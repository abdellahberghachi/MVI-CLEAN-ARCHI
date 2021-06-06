package com.example.mvicleanarchi.ui.home

import com.simple.mvi.common.ViewIntent

sealed class HomeIntent : ViewIntent {
    object LoadAllCharacters : HomeIntent()
    class SearchByName(val key: String) : HomeIntent()
    object ClearSearch : HomeIntent()
}