package com.example.mvicleanarchi.ui.home

import com.example.domain.usecase.GetCharactersUseCase
import com.example.mvicleanarchi.common.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(private val useCase: GetCharactersUseCase) :
    BaseViewModel<HomeIntent, HomeAction, HomeState>() {
    override fun intentToAction(intent: HomeIntent): HomeAction {
        return when (intent) {
            is HomeIntent.LoadAllCharacters -> HomeAction.LoadAllCharacters
            is HomeIntent.ClearSearch -> HomeAction.LoadAllCharacters
            is HomeIntent.SearchByName -> HomeAction.SearchCharacters(intent.key)
        }
    }


    override fun handleAction(action: HomeAction) {
        launchOnUI {
            when (action) {
                is HomeAction.LoadAllCharacters -> {
                    useCase().onStart {
                        mState.postValue(HomeState.ShowLoading)
                    }.catch { mState.postValue(HomeState.Exception) }
                        .collect {
                        mState.postValue(HomeState.ResultAllPersona(it))
                    }
                }

            }
        }
    }
}