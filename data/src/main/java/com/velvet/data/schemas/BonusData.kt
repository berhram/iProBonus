package com.velvet.data.schemas

import com.google.gson.annotations.SerializedName

data class BonusData(
    @SerializedName("typeBonusName") val typeBonusName: String?,
    @SerializedName("currentQuantity") val currentQuantity: Double,
    @SerializedName("forBurningQuantity") val forBurningQuantity: Double,
    @SerializedName("dateBurning") val dateBurning: String
)
