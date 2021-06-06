package com.example.mvicleanarchi.ui.home.ui

import android.os.Bundle
import android.util.Log
import com.example.mvicleanarchi.R
import com.example.mvicleanarchi.common.BaseActivity
import com.example.mvicleanarchi.ui.home.HomeAction
import com.example.mvicleanarchi.ui.home.HomeIntent
import com.example.mvicleanarchi.ui.home.HomeState
import com.example.mvicleanarchi.ui.home.HomeViewModel

class HomeActivity : BaseActivity<HomeIntent, HomeAction, HomeState, HomeViewModel>(HomeViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getLayoutResId(): Int = R.layout.activity_home

    override fun initUI() {
    }

    override fun initDATA() {
    }

    override fun initEVENT() {
    }

    override fun render(state: HomeState) {

        Log.d("HOMEACTIVITY",state.toString())
    }
}