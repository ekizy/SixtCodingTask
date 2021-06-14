package com.ekiz.sixtcodingtask.injection.module

import com.ekiz.sixtcodingtask.BuildConfig
import com.ekiz.sixtcodingtask.data.api.services.CarsService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClient.Builder().build())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideCarsService(retrofit: Retrofit): CarsService {
        return retrofit.create(CarsService::class.java)
    }

}