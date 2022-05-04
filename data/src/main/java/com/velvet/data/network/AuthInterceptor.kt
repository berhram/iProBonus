package com.velvet.data.network

import com.velvet.data.Settings.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("AccessKey", API_KEY)
        return chain.proceed(requestBuilder.build())
    }
}