package app.devzenix.docscan.domain.repository

import app.devzenix.docscan.domain.model.AppNotification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun getDeliveredNotifications(): Flow<List<AppNotification>>
    fun getUnreadCount(): Flow<Long>
    suspend fun insertNotification(notification: AppNotification)
    suspend fun markAllAsRead()
    suspend fun deleteByDocumentId(documentId: String)
}
