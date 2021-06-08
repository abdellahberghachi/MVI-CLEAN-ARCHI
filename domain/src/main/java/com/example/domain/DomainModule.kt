package com.example.domain

import com.example.domain.usecase.GetRepoUseCase
import org.koin.dsl.module

val domainModule = module {


  factory { GetRepoUseCase(userRepository = get()) }

}
