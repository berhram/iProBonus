package com.velvet.data.schemas

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("idClient") val clientId: String?,
    @SerializedName("accessToken") val accessToken: String?,
    @SerializedName("paramName") val device: String?,
    @SerializedName("paramValue") val deviceId: String?,
    @SerializedName("latitude") val latitude: Float,
    @SerializedName("longitude") val longitude: Float,
    @SerializedName("sourceQuery") val sourceQuery: Int
)
