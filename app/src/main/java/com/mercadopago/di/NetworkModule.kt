package com.mercadopago.di

import com.mercadopago.BuildConfig
import com.mercadopago.net.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun cocktailApiService(): ApiService {
        val converterFactory = GsonConverterFactory.create()

        val retrofitBuilder =  Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(converterFactory)

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            val okHttpClient = OkHttpClient.Builder().apply {
                connectTimeout(5, TimeUnit.SECONDS)
                addInterceptor(httpLoggingInterceptor)
            }.build()
            retrofitBuilder.client(okHttpClient)
        }

        return retrofitBuilder.build().create(ApiService::class.java)
    }

}