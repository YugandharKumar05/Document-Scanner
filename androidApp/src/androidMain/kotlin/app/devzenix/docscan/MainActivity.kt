package app.devzenix.docscan

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import app.devzenix.docscan.App
import app.devzenix.docscan.config.FeatureFlags
import app.devzenix.docscan.platform.FilePicker
import app.devzenix.docscan.platform.GoogleAuthProvider
import app.devzenix.docscan.platform.NotificationPermissionHandler

class MainActivity : ComponentActivity() {

    private var pendingPermissionCallback: ((Boolean) -> Unit)? = null
    private var pendingFilePickCallback: ((android.net.Uri?) -> Unit)? = null

    private val filePickerLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        pendingFilePickCallback?.invoke(uri)
        pendingFilePickCallback = null
    }

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        pendingPermissionCallback?.invoke(granted)
        pendingPermissionCallback = null
    }

    private val authConsentLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        val callback = GoogleAuthProvider.onConsentResult
        GoogleAuthProvider.onConsentResult = null

        if (callback == null) {
            Log.w("MainActivity", "Auth consent result received but no callback registered")
            return@registerForActivityResult
        }

        try {
            val authResult = Identity
                .getAuthorizationClient(this)
                .getAuthorizationResultFromIntent(result.data)
            callback(Result.success(authResult))
        } catch (e: ApiException) {
            callback(Result.failure(e))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        NotificationPermissionHandler.permissionLauncher = { onResult ->
            pendingPermissionCallback = onResult
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
        FilePicker.pickerLauncher = { onResult ->
            pendingFilePickCallback = onResult
            filePickerLauncher.launch(arrayOf("image/*", "application/pdf"))
        }
        if (FeatureFlags.GOOGLE_SIGN_IN_ENABLED) {
            GoogleAuthProvider.consentLauncher = { request: IntentSenderRequest ->
                authConsentLauncher.launch(request)
            }
        }
        enableEdgeToEdge()
        setContent {
            App()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        NotificationPermissionHandler.permissionLauncher = null
        FilePicker.pickerLauncher = null
        if (FeatureFlags.GOOGLE_SIGN_IN_ENABLED) {
            GoogleAuthProvider.consentLauncher = null
        }
    }
}
