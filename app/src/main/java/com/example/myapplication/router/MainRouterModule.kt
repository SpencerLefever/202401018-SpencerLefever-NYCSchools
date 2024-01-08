package com.example.myapplication.router

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainRouterModule {

    @Provides
    fun providesMainRouter(): MainRouter {
        return MainRouterImpl()
    }
}