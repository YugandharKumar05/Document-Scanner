package app.devzenix.docscan.platform

expect suspend fun renderPdfPage(pdfBytes: ByteArray, pageIndex: Int): ByteArray?

expect suspend fun getPdfPageCount(pdfBytes: ByteArray): Int
