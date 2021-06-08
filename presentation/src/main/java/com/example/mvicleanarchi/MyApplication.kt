package com.example.mvicleanarchi

import android.app.Application
import com.example.data.dataModule
import com.example.domain.domainModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import kotlin.time.ExperimentalTime


@ExperimentalTime
class MyApplication : Application(){
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                dataModule,
                domainModule,
                mainModule
            )
        }
    }
}