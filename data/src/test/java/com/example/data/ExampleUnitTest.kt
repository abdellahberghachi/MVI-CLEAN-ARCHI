package com.example.data

import com.example.data.mapper.PostResponseToPostDomainMapper
import com.example.data.remote.ApiService
import com.example.data.remote.PostResponse
import com.example.domain.entities.Post
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import java.io.IOException

@ExperimentalCoroutinesApi
class ExampleUnitTest {


    val testCoroutineDispatcher = TestCoroutineDispatcher()
    val apiService = mock<ApiService>()
    val repository = RepoRepositoryImpl(apiService, PostResponseToPostDomainMapper(),testCoroutineDispatcher)


    @InternalCoroutinesApi
    @Test
    fun `flow emits successfully`() = runBlocking {

        // Mock API Service
        val posts = listOf(
            PostResponse("1", 1, "adqsdqsd", 1),
            PostResponse("1", 1, "adqsdqsd", 1),
            PostResponse("1", 1, "adqsdqsd", 1),
            PostResponse("1", 1, "adqsdqsd", 1),
        )
        apiService.stub {
            onBlocking { getPosts() } doReturn posts
        }
        // Test
        val flow = repository.getAllRepos()
        // Verify
        flow.collect {
           assertEquals(it.size,posts.size)

        }
    }



    @Test(expected =IOException::class )
    fun `should retry with error`() =
        testCoroutineDispatcher.runBlockingTest {
            apiService.stub {
                onBlocking { getPosts() } doAnswer {
                    throw IOException()
                }
            }

            val flow = repository.getAllRepos()

            flow.collect {
                assertEquals(it.size,0)
            }
        }
}