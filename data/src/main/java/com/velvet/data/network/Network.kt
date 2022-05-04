package com.velvet.data.network

import com.velvet.data.schemas.AuthRequest
import com.velvet.data.schemas.BonusData

interface Network {
    suspend fun getBonuses(authRequest: AuthRequest) : Result<BonusData>
}