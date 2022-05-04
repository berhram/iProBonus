package com.velvet.data.schemas

import com.google.gson.annotations.SerializedName

internal data class BonusResponse(
    @SerializedName("resultOperation") val result: CallResult,
    @SerializedName("data") val data: BonusData
)