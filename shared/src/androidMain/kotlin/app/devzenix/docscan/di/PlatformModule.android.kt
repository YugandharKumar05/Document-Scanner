package app.devzenix.docscan.di

import app.devzenix.docscan.data.local.DatabaseDriverFactory
import app.devzenix.docscan.platform.FilePicker
import app.devzenix.docscan.platform.ImageStorage
import app.devzenix.docscan.platform.GoogleAuthProvider
import app.devzenix.docscan.platform.NotificationPermissionHandler
import app.devzenix.docscan.platform.NotificationScheduler
import app.devzenix.docscan.platform.PlatformContext
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { PlatformContext(androidContext()) }
    single { DatabaseDriverFactory(androidContext()) }
    single { ImageStorage(androidContext()) }
    single { NotificationScheduler(androidContext()) }
    single { NotificationPermissionHandler(androidContext()) }
    single { GoogleAuthProvider(androidContext()) }
    single { FilePicker(androidContext()) }
}
