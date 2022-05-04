package com.velvet.data.schemas

import com.google.gson.annotations.SerializedName

internal data class CallResult(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String?,
    @SerializedName("messageDev") val messageDev: String?,
    @SerializedName("codeResult") val codeResult: Int,
    @SerializedName("duration") val duration: Double,
    @SerializedName("idLog") val idLog: String,
)
