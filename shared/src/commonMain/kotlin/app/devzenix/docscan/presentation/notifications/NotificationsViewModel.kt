package app.devzenix.docscan.presentation.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.devzenix.docscan.domain.model.AppNotification
import app.devzenix.docscan.domain.repository.NotificationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotificationsViewModel(
    private val notificationRepository: NotificationRepository
) : ViewModel() {

    val notifications: StateFlow<List<AppNotification>> =
        notificationRepository.getDeliveredNotifications()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun markAllAsReadAfterDelay() {
        viewModelScope.launch {
            delay(2000)
            notificationRepository.markAllAsRead()
        }
    }
}
