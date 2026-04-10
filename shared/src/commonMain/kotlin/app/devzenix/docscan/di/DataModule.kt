package app.devzenix.docscan.di

import app.devzenix.docscan.data.local.DatabaseDriverFactory
import app.devzenix.docscan.data.local.db.DocumentDatabase
import app.devzenix.docscan.data.remote.DocumentAiMapper
import app.devzenix.docscan.data.remote.DocumentAiService
import app.devzenix.docscan.data.remote.ProxyConfig
import app.devzenix.docscan.data.remote.UserProfileService
import app.devzenix.docscan.data.repository.AppSettingsRepositoryImpl
import app.devzenix.docscan.data.repository.DocumentRepositoryImpl
import app.devzenix.docscan.data.repository.NotificationRepositoryImpl
import app.devzenix.docscan.data.repository.OcrRepositoryImpl
import app.devzenix.docscan.domain.repository.AppSettingsRepository
import app.devzenix.docscan.domain.repository.DocumentRepository
import app.devzenix.docscan.domain.repository.NotificationRepository
import app.devzenix.docscan.domain.repository.OcrRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {

    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = false
        }
    }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(get())
            }
            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        app.devzenix.docscan.platform.AppLogger.d("KtorHttp", message)
                    }
                }
            }
        }
    }

    single {
        val driverFactory: DatabaseDriverFactory = get()
        DocumentDatabase(driverFactory.createDriver())
    }

    single { DocumentAiMapper() }

    single { DocumentAiService(get(), get<ProxyConfig>()) }

    single<DocumentRepository> { DocumentRepositoryImpl(get()) }

    single<NotificationRepository> { NotificationRepositoryImpl(get()) }

    single<OcrRepository> { OcrRepositoryImpl(get(), get(), get()) }

    single<AppSettingsRepository> { AppSettingsRepositoryImpl(get()) }

    single { UserProfileService(get()) }
}
