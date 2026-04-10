package app.devzenix.docscan.platform

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

expect class NotificationScheduler {
    fun scheduleReminder(
        documentId: String,
        documentName: String,
        expiryDate: LocalDate,
        reminderDate: LocalDateTime,
        daysBefore: Int
    )

    fun cancelReminders(documentId: String)
    fun cancelAllReminders()
}
