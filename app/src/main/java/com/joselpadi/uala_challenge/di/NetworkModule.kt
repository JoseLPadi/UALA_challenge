package com.joselpadi.uala_challenge.di

import com.joselpadi.uala_challenge.data.remote.api.CitiesRetrofitInterfeace
import com.joselpadi.uala_challenge.data.remote.api.WeatherRetrofitInterface
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    @Named("cities")
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CITIES_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCitiesApi(
        @Named("cities") retrofit: Retrofit
    ): CitiesRetrofitInterfeace {
        return retrofit.create(CitiesRetrofitInterfeace::class.java)
    }

    @Provides
    @Singleton
    @Named("weather")
    fun provideWeatheretrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(
        @Named("weather") retrofit: Retrofit
    ): WeatherRetrofitInterface {
        return retrofit.create(WeatherRetrofitInterface::class.java)
    }


    private companion object {
        const val CITIES_BASE_URL =
            "https://gist.githubusercontent.com/hernan-uala/"
        const val WEATHER_BASE_URL =
            "https://api.weatherbit.io/v2.0/"
    }
}