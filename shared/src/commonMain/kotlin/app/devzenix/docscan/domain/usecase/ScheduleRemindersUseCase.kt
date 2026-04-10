package app.devzenix.docscan.domain.usecase

import app.devzenix.docscan.domain.model.AppNotification
import app.devzenix.docscan.domain.model.Document
import app.devzenix.docscan.domain.repository.NotificationRepository
import app.devzenix.docscan.platform.NotificationScheduler
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.Clock as DateTimeClock
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

class ScheduleRemindersUseCase(
    private val notificationScheduler: NotificationScheduler,
    private val notificationRepository: NotificationRepository
) {
    operator fun invoke(document: Document) {
        // Cancel existing reminders and clear old notification records
        notificationScheduler.cancelReminders(document.id)
        runBlocking { notificationRepository.deleteByDocumentId(document.id) }

        val expiryDate = document.expiryDate ?: return
        val tz = TimeZone.currentSystemDefault()
        val today = DateTimeClock.System.todayIn(tz)
        val nowTime = DateTimeClock.System.now().toLocalDateTime(tz).time
        val reminderTime = document.reminderTime

        document.reminderDays.forEach { daysBefore ->
            val reminderDate = expiryDate.minus(daysBefore, DateTimeUnit.DAY)

            val scheduledDateTime = when {
                reminderDate > today -> reminderDate.atTime(reminderTime)
                reminderDate == today && reminderTime > nowTime -> reminderDate.atTime(reminderTime)
                reminderDate == today -> {
                    // Time already passed today — schedule 5 seconds from now
                    val futureInstant = DateTimeClock.System.now().plus(5, DateTimeUnit.SECOND)
                    futureInstant.toLocalDateTime(tz)
                }
                else -> return@forEach // Past date, skip
            }

            notificationScheduler.scheduleReminder(
                documentId = document.id,
                documentName = document.name,
                expiryDate = expiryDate,
                reminderDate = scheduledDateTime,
                daysBefore = daysBefore
            )

            // Record notification for in-app display
            runBlocking {
                notificationRepository.insertNotification(
                    AppNotification(
                        id = "${document.id}_$daysBefore",
                        documentId = document.id,
                        documentName = document.name,
                        category = document.category,
                        expiryDate = document.expiryDate,
                        daysBefore = daysBefore,
                        scheduledAt = scheduledDateTime,
                        isRead = false
                    )
                )
            }
        }
    }
}
