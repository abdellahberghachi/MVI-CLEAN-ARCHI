package com.example.domain.usecase

import com.example.domain.repository.CharactersRepository

class GetCharactersUseCase(private val userRepository: CharactersRepository) {
  operator fun invoke() = userRepository.getAllCharacters()
}
