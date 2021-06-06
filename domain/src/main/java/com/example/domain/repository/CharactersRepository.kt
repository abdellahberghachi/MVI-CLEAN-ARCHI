package com.example.domain.repository

import com.example.domain.entities.Post
import kotlinx.coroutines.flow.Flow


interface CharactersRepository {
    fun getAllCharacters(): Flow<List<Post>>

}