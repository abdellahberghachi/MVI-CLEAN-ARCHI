package com.example.mvicleanarchi.ui.home.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope

import com.example.mvicleanarchi.databinding.ActivityHomeBinding
import com.example.mvicleanarchi.ui.home.HomeContract
import com.example.mvicleanarchi.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class HomeActivity : AppCompatActivity() {

    lateinit var adapter: RepoAdapter
    @ExperimentalCoroutinesApi
    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
        initObservers()
    }

    private fun setup() {
        adapter= RepoAdapter()
        binding.let {
            homeListRepos.adapter=adapter
        }
    }

    /**
     * Initialize Observers
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {

                when (it.repoState) {
                    is HomeContract.RepoState.Idle -> {
                        binding.homeProgress.isVisible = false
                        //binding.button.isVisible = false
                    }
                    is HomeContract.RepoState.Loading -> {

                        binding.homeProgress.isVisible = true


                    }
                    is HomeContract.RepoState.Success -> {
                        binding.homeProgress.isVisible = false
                        adapter.submitList(it.repoState.posts)
                        Log.e("COLLECT", it.repoState.posts.toString())
                    }
                }
            }
        }


        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                when (it) {
                    is HomeContract.Effect.ShowToast -> {
                        showToast("Error, number is even")
                    }
                }
            }
        }
    }

    /**
     * Show simple toast message
     */
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}