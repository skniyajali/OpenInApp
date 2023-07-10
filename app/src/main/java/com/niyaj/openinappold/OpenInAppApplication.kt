package com.niyaj.openinappold

import android.app.Application
import com.niyaj.openinappold.features.common.api.SessionManager
import com.niyaj.openinappold.features.common.utils.Constants.TOKEN
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class OpenInAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }


        val sessionManager = SessionManager(this.applicationContext)

        val token = sessionManager.fetchAuthToken()

        if (token == null) {
            sessionManager.saveAuthToken(TOKEN)
        }
    }
}