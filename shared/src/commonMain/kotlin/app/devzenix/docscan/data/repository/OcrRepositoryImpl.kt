package app.devzenix.docscan.data.repository

import app.devzenix.docscan.data.remote.DocumentAiMapper
import app.devzenix.docscan.data.remote.DocumentAiService
import app.devzenix.docscan.domain.model.ScanResult
import app.devzenix.docscan.domain.repository.OcrRepository
import kotlinx.serialization.json.Json

class OcrRepositoryImpl(
    private val documentAiService: DocumentAiService,
    private val mapper: DocumentAiMapper,
    private val json: Json
) : OcrRepository {

    override suspend fun processDocument(imageBytes: ByteArray, mimeType: String): ScanResult {
        val response = documentAiService.processDocument(imageBytes, mimeType)
        val rawJson = try {
            json.encodeToString(
                app.devzenix.docscan.data.remote.ProcessResponse.serializer(),
                response
            )
        } catch (_: Exception) {
            null
        }
        return mapper.mapToScanResult(response, rawJson)
    }
}
