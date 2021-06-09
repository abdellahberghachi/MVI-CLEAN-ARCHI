package com.example.mvicleanarchi.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetRepoUseCase
import com.example.mvicleanarchi.common.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel constructor(val useCase: GetRepoUseCase) :
    BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {


    init {
        handleEvent(HomeContract.Event.GetGithubRepo)
    }

    override fun createInitialState(): HomeContract.State {
        return HomeContract.State(
            HomeContract.RepoState.Idle
        )
    }

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.GetGithubRepo -> {
                getRepo()
            }
            is HomeContract.Event.OnShowToastClicked -> {
                setEffect { HomeContract.Effect.ShowToast }
            }
        }
    }

    private fun getRepo() {
        viewModelScope.launch {
            // Set Loading
            setState { copy(repoState = HomeContract.RepoState.Loading) }
            try {
                useCase().collect {
                    setState {
                        copy(
                            repoState = HomeContract.RepoState.Success(
                                posts = it
                            )
                        )
                    }
                }

            } catch (exception: Exception) {
                setState { copy(repoState = HomeContract.RepoState.Idle) }
                setEffect { HomeContract.Effect.ShowToast }

            }
        }
    }
}