package com.example.data

import com.example.data.dispatchers.CoroutineDispatchers
import com.example.data.dispatchers.CoroutineDispatchersImpl
import com.example.data.mapper.PostDomainToPostResponseMapper
import com.example.data.mapper.PostResponseToPostDomainMapper
import com.example.data.remote.ApiService
import com.example.domain.repository.RepoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.time.ExperimentalTime


@ExperimentalTime
@ExperimentalCoroutinesApi
val dataModule = module {

    single { CoroutineDispatchersImpl().io }

    single { ApiService(retrofit = get()) }

    single {
        provideRetrofit(
            okHttpClient = get()
        )
    }

    single { provideOkHttpClient() }

    factory { PostResponseToPostDomainMapper() }

    factory { PostDomainToPostResponseMapper() }



    single<RepoRepository> {
        RepoRepositoryImpl(
            api = get(),
            responseToDomain = get<PostResponseToPostDomainMapper>(),
            dispatcher = get()
        )
    }
}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_BASE_URL)
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)

        .build()
}
