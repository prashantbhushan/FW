package com.prashant.fwtest.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AndroidContextModule {
    @Binds
    @Singleton
    abstract fun application(app: Application): Context
}