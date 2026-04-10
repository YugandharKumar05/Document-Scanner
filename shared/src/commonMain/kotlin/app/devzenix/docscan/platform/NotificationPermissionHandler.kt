package app.devzenix.docscan.platform

expect class NotificationPermissionHandler {
    fun hasPermission(): Boolean
    fun requestPermission(onResult: (Boolean) -> Unit)
}
