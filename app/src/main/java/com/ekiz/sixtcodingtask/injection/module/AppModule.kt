package com.ekiz.sixtcodingtask.injection.module

import android.content.Context
import com.ekiz.sixtcodingtask.application.CodingTaskApplication
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: CodingTaskApplication): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

}