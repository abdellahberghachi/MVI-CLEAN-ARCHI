package com.example.data.remote

import com.example.data.common.PATH_CHARACTER
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET(PATH_CHARACTER)
    suspend fun getPosts(
    ): List<PostResponse>

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<ApiService>()
    }

}