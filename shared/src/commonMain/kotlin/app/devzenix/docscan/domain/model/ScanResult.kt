package app.devzenix.docscan.domain.model

import app.devzenix.docscan.domain.model.DocumentCategory
import kotlinx.datetime.LocalDate

data class ScanResult(
    val extractedName: String?,
    val expiryDate: LocalDate?,
    val confidence: Float?,
    val fullText: String?,
    val rawResponse: String?,
    val detectedCategory: DocumentCategory? = null
)
