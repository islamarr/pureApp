package com.islam.pureApp.common

import android.app.Application
import com.islam.pureApp.di.AppContainer

class PureApp : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainer(this)
    }

}