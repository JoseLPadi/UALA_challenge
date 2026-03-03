package com.joselpadi.uala_challenge

import android.app.Application
import com.joselpadi.uala_challenge.di.AppComponent
import com.joselpadi.uala_challenge.di.DaggerAppComponent

class UALAChallengeApplication  : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}