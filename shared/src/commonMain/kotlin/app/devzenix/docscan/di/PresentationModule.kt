package app.devzenix.docscan.di

import app.devzenix.docscan.presentation.auth.AuthViewModel
import app.devzenix.docscan.presentation.detail.DetailViewModel
import app.devzenix.docscan.presentation.main.DocumentsViewModel
import app.devzenix.docscan.presentation.notifications.NotificationsViewModel
import app.devzenix.docscan.presentation.pulpit.DashboardViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::DocumentsViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::AuthViewModel)
    viewModelOf(::DashboardViewModel)
    viewModelOf(::NotificationsViewModel)
}
