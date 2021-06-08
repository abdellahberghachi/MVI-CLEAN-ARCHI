package com.example.mvicleanarchi.ui.home

import com.example.domain.entities.Post
import com.example.mvicleanarchi.common.base.UiEffect
import com.example.mvicleanarchi.common.base.UiEvent
import com.example.mvicleanarchi.common.base.UiState


class HomeContract {

    sealed class Event : UiEvent {
        object GetGithubRepo : Event()
        object OnShowToastClicked : Event()
    }

    data class State(
        val repoState: RepoState
    ) : UiState

    sealed class RepoState {
        object Idle : RepoState()
        object Loading : RepoState()
        data class Success(val posts: List<Post>) : RepoState()
    }

    sealed class Effect : UiEffect {

        object ShowToast : Effect()

    }

}