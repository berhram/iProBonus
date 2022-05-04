package com.velvet.data.network

import com.velvet.data.schemas.AuthRequest
import com.velvet.data.schemas.AuthResponse
import com.velvet.data.schemas.BonusResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface Api {
    @POST("clients/accesstoken")
    fun refreshAccessToken(@Body request: AuthRequest) : Call<AuthResponse>

    @GET("ibonus/generalinfo/{accessToken}")
    fun getBonuses(@Path("accessToken") accessToken: String) : Call<BonusResponse>
}