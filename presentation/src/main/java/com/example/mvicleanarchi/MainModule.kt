package com.example.mvicleanarchi

import com.example.mvicleanarchi.ui.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalTime
val mainModule = module {

  viewModel { HomeViewModel(useCase = get()) }
}
