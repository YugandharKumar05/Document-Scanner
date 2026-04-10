package app.devzenix.docscan.domain.repository

import app.devzenix.docscan.domain.model.ScanResult

interface OcrRepository {
    suspend fun processDocument(imageBytes: ByteArray, mimeType: String): ScanResult
}
