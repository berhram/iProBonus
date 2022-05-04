package com.velvet.data

import android.content.Context
import android.content.SharedPreferences

class TokenStore(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS,
        Context.MODE_PRIVATE
    )

    fun saveToken(accessToken: String) {
        preferences.edit().putString(ACCESS_TOKEN, accessToken).apply()
    }

    fun getToken() : String {
        return preferences.getString(ACCESS_TOKEN, "") ?: ""
    }

    companion object {
        private const val SHARED_PREFS = "ProgressTerra exam task shared prefs"
        private const val ACCESS_TOKEN = "Access token"
    }
}