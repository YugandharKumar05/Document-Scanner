package app.devzenix.docscan

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import app.devzenix.docscan.domain.repository.AppSettingsRepository
import app.devzenix.docscan.presentation.consent.ConsentScreen
import app.devzenix.docscan.presentation.navigation.NavGraph
import app.devzenix.docscan.presentation.theme.DevZenixTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


@Composable
fun App() {
    DevZenixTheme(darkTheme = isSystemInDarkTheme()) {
//        val appSettingsRepository: AppSettingsRepository = koinInject()
//        val termsAccepted by appSettingsRepository.isTermsAccepted().collectAsState(initial = null)
//        val scope = rememberCoroutineScope()
//        when (termsAccepted) {
//            null -> { /* Loading — show nothing while reading DB */ }
//            false -> ConsentScreen(
//                onAccepted = {
//                    scope.launch { appSettingsRepository.setTermsAccepted(true) }
//                }
//            )
//            true -> NavGraph()
//        }
        NavGraph()
    }
}
