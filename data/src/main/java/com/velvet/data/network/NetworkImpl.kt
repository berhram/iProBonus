package com.velvet.data.network

import com.velvet.data.BuildConfig
import com.velvet.data.Settings.BASE_URL
import com.velvet.data.TokenStore
import com.velvet.data.schemas.AuthRequest
import com.velvet.data.schemas.AuthResponse
import com.velvet.data.schemas.BonusData
import com.velvet.data.schemas.BonusResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkImpl @Inject constructor(authInterceptor: AuthInterceptor, private val tokenStore: TokenStore) :
    Network {
    private val service: Api

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        service = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(logging)
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    private suspend fun retrieveAccessToken(authRequest: AuthRequest) : Result<AuthResponse> {
        return try {
            Result.success(service.refreshAccessToken(authRequest).await())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun bonusCall() : Result<BonusResponse> {
        return try {
            Result.success(service.getBonuses(tokenStore.getToken()).await())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBonuses(authRequest: AuthRequest): Result<BonusData> {
        val tokenCall = retrieveAccessToken(authRequest)
        return if (tokenCall.isSuccess) {
            tokenStore.saveToken(tokenCall.getOrNull()!!.accessToken ?: "")
            val bonusCall = bonusCall()
            if (bonusCall.isSuccess) {
                Result.success(bonusCall.getOrNull()!!.data)
            } else {
                Result.failure(bonusCall.exceptionOrNull()!!)
            }
        } else {
            Result.failure(tokenCall.exceptionOrNull()!!)
        }
    }
}