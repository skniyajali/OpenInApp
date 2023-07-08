package com.niyaj.openinappold.features.common.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(context: Context) : Interceptor {

    private val sessionManager = SessionManager(context)


    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let {
            requestBuilder
                .addHeader("Authorization", "Bearer $it")
        }

        requestBuilder
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(requestBuilder.build())
    }
}