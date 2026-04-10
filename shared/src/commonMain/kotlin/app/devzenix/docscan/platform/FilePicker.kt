package app.devzenix.docscan.platform

data class PickedFile(val bytes: ByteArray, val mimeType: String, val fileName: String)

expect class FilePicker {
    suspend fun pickFile(): PickedFile?
    suspend fun pickPhotoFromGallery(): PickedFile?
}
