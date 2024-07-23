package data

import domain.Repository
import model.User
import network.APIClient

class MyRepository(private val apiClient: APIClient) : Repository {
    override suspend fun getPosts(perPage:Int):User {
       return apiClient.getPosts(perPage)
    }
}