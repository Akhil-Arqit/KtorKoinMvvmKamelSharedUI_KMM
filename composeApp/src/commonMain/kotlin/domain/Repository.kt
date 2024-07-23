package domain

import model.User

interface Repository {
    suspend fun getPosts(perPage:Int) : User
}