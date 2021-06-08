package com.example.data

import com.example.domain.repository.RepoRepository
import com.example.data.remote.ApiService
import com.example.data.remote.PostResponse
import com.example.domain.entities.Post
import com.example.data.common.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersRepositoryImpl(
    private val api: ApiService,
    private val responseToDomain: Mapper<PostResponse, Post>
) : RepoRepository {

    override fun getAllCharacters(): Flow<List<Post>> = flow {
        emit(api.getPosts().map(responseToDomain))
    }

}