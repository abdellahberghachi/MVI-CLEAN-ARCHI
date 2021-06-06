package com.example.data

import com.example.domain.repository.CharactersRepository
import com.example.data.remote.ApiService
import com.example.data.remote.PostResponse
import com.example.domain.entities.Post
import com.hoc.flowmvi.core.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersRepositoryImpl(
    private val api: ApiService,
    private val responseToDomain: Mapper<PostResponse, Post>
) : CharactersRepository {

    override fun getAllCharacters(): Flow<List<Post>> = flow {
        emit(api.getPosts(0, 10).map(responseToDomain))
    }

}