package com.velvet.data.di

import android.content.Context
import com.velvet.data.TokenStore
import com.velvet.data.network.AuthInterceptor
import com.velvet.data.network.Network
import com.velvet.data.network.NetworkImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideNetwork(authInterceptor: AuthInterceptor, tokenStore: TokenStore) : Network {
        return NetworkImpl(authInterceptor = authInterceptor, tokenStore = tokenStore)
    }

    @Provides
    @Singleton
    fun providesTokenStore() : TokenStore {
        return TokenStore(context)
    }

    @Provides
    @Singleton
    fun providesInterceptor() : AuthInterceptor {
        return AuthInterceptor()
    }
}