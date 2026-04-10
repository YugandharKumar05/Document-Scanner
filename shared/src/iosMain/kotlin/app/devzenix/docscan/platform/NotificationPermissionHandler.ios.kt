package app.devzenix.docscan.platform

actual class NotificationPermissionHandler {
    actual fun hasPermission(): Boolean = true

    actual fun requestPermission(onResult: (Boolean) -> Unit) {
        onResult(true)
    }
}
