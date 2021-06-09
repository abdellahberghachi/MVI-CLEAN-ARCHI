package com.example.domain.repository

import com.example.domain.entities.Post
import kotlinx.coroutines.flow.Flow


interface RepoRepository {
    fun getAllRepos(): Flow<List<Post>>

}