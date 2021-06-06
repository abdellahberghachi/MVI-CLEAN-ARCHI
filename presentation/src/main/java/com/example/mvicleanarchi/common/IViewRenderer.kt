package com.example.mvicleanarchi.common


interface IViewRenderer<STATE> {
    fun render(state: STATE)
}