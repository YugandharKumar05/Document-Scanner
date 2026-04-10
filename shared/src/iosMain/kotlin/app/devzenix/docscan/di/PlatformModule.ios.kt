package app.devzenix.docscan.di

import app.devzenix.docscan.data.local.DatabaseDriverFactory
import app.devzenix.docscan.platform.FilePicker
import app.devzenix.docscan.platform.ImageStorage
import app.devzenix.docscan.platform.GoogleAuthProvider
import app.devzenix.docscan.platform.NotificationPermissionHandler
import app.devzenix.docscan.platform.NotificationScheduler
import app.devzenix.docscan.platform.PlatformContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { PlatformContext() }
    single { DatabaseDriverFactory() }
    single { ImageStorage() }
    single { NotificationScheduler() }
    single { NotificationPermissionHandler() }
    single { GoogleAuthProvider() }
    single { FilePicker() }
}
