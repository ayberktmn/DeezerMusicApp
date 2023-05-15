package com.ayberk.deezermusicapp.Module


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    var baseURL = "https://api.deezer.com/"

    @Provides
    @Singleton
    fun getRetrofitServiceInstance(retrofit: Retrofit): com.ayberk.deezermusicapp.Retrofit.Retrofit {
        return retrofit.create(com.ayberk.deezermusicapp.Retrofit.Retrofit::class.java)
    }

    @Singleton
    @Provides
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}