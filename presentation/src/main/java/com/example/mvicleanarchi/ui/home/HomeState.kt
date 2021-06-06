package com.example.mvicleanarchi.ui.home

import com.example.data.common.CallErrors
import com.example.domain.entities.Post
import com.simple.mvi.common.ViewState

sealed class HomeState : ViewState {

    object ShowLoading : HomeState()
    data class ResultAllPersona(val data : List<Post>): HomeState()
    data class ResultSearch(val data : List<Post>): HomeState()
    object Exception : HomeState()

}