package app.devzenix.docscan.platform

expect class GoogleAuthProvider {
    suspend fun getAccessToken(): String
    fun clearToken()
}
