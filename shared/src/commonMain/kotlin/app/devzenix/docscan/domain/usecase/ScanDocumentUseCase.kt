package app.devzenix.docscan.domain.usecase

import app.devzenix.docscan.domain.model.ScanResult
import app.devzenix.docscan.domain.repository.OcrRepository
import app.devzenix.docscan.platform.AppLogger

class ScanDocumentUseCase(
    private val ocrRepository: OcrRepository
) {
    suspend operator fun invoke(imageBytes: ByteArray, mimeType: String): Result<ScanResult> {
        AppLogger.d(TAG, "invoke called, imageBytes=${imageBytes.size}, mimeType=$mimeType")
        return try {
            val result = ocrRepository.processDocument(imageBytes, mimeType)
            AppLogger.d(TAG, "SUCCESS - name=${result.extractedName}, expiryDate=${result.expiryDate}, confidence=${result.confidence}")
            Result.success(result)
        } catch (e: Exception) {
            AppLogger.e(TAG, "FAILED - ${e::class.simpleName}: ${e.message}", e)
            Result.failure(e)
        }
    }

    companion object {
        private const val TAG = "ScanDocumentUseCase"
    }
}
