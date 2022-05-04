package com.velvet.data.schemas

import com.google.gson.annotations.SerializedName

internal data class AuthResponse(
    @SerializedName("result") val authResult: CallResult,
    @SerializedName("accessToken") val accessToken: String?
)
