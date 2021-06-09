package com.example.data

import com.example.data.common.Mapper
import com.example.data.remote.ApiService
import com.example.data.remote.PostResponse
import com.example.domain.entities.Post
import com.example.domain.repository.RepoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RepoRepositoryImpl(
    private val api: ApiService,
    private val responseToDomain: Mapper<PostResponse, Post>,
    private val dispatcher: CoroutineDispatcher
) : RepoRepository {

    override fun getAllRepos(): Flow<List<Post>> =
        flow {
        emit((api.getPosts().map(responseToDomain)))
    }.flowOn(dispatcher)

}