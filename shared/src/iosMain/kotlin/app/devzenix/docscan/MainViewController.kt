package app.devzenix.docscan

import androidx.compose.ui.window.ComposeUIViewController
import app.devzenix.docscan.data.remote.ProxyConfig
import app.devzenix.docscan.di.appModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin(proxyUrl: String, apiKey: String) {
    startKoin {
        modules(
            appModules + module {
                single { ProxyConfig(url = proxyUrl, apiKey = apiKey) }
            }
        )
    }
}
