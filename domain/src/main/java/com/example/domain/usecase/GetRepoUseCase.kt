package com.example.domain.usecase

import com.example.domain.repository.RepoRepository

class GetRepoUseCase(private val userRepository: RepoRepository) {
  operator fun invoke() = userRepository.getAllCharacters()
}
