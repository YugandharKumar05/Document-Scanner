package app.devzenix.docscan.di

import app.devzenix.docscan.domain.usecase.ScanDocumentUseCase
import app.devzenix.docscan.domain.usecase.ScheduleRemindersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { ScanDocumentUseCase(get()) }
    factory { ScheduleRemindersUseCase(get(), get()) }
}
