// src/ApiClient.kt
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

@Serializable
data class ApiPost(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)

class ApiClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    // Базова URL для всіх запитів
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    // GET запит для отримання всіх постів
    suspend fun getPosts(): List<ApiPost> {
        return client.get("$baseUrl/posts") {
            header("X-Custom-Header", "TestValue")
        }.body()
    }

    // GET запит з query параметрами
    suspend fun getPostById(postId: Int): ApiPost {
        return client.get("$baseUrl/posts/$postId") {
            parameter("detailed", true)
        }.body()
    }

    // POST запит для створення нового поста
    suspend fun createPost(title: String, body: String, userId: Int): ApiPost {
        return client.post("$baseUrl/posts") {
            contentType(ContentType.Application.Json)
            setBody(ApiPost(0, title, body, userId))
        }.body()
    }

    // PUT запит для оновлення поста
    suspend fun updatePost(postId: Int, title: String, body: String, userId: Int): ApiPost {
        return client.put("$baseUrl/posts/$postId") {
            contentType(ContentType.Application.Json)
            setBody(ApiPost(postId, title, body, userId))
        }.body()
    }

    // DELETE запит для видалення поста
    suspend fun deletePost(postId: Int): HttpResponse {
        return client.delete("$baseUrl/posts/$postId")
    }
}
