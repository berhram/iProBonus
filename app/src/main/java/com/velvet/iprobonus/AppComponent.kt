package com.velvet.iprobonus

import com.velvet.data.di.DataModule
import com.velvet.iprobonus.ui.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataModule::class])
@Singleton
interface AppComponent {
    fun appViewModelFactory() : ViewModelFactory
}