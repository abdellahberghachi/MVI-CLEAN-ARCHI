package com.example.mvicleanarchi.ui.home

import com.simple.mvi.common.ViewAction

sealed class HomeAction : ViewAction {
    object LoadAllCharacters : HomeAction()
    class SearchCharacters(key: String) : HomeAction()
}