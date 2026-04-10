package app.devzenix.docscan.presentation.camera

import androidx.compose.runtime.Composable

@Composable
expect fun CameraScreen(
    onImageCaptured: (String) -> Unit,
    onBack: () -> Unit
)
