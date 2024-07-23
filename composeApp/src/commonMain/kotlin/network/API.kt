package network

import domain.Repository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import model.User

val JsonSerializer = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
    encodeDefaults = true
    classDiscriminator = "#class"
}

class APIClient : Repository {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(JsonSerializer)
        }
        install(Logging) {
            level = LogLevel.ALL
        }
    }


    override suspend fun getPosts(perPage:Int) : User {
        val posts = httpClient.get("https://reqres.in/api/users?page=1&per_page=$perPage")
        return Json.decodeFromString<User>(posts.body())
    }
}