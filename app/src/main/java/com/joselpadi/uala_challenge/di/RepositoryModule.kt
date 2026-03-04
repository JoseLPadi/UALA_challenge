package com.joselpadi.uala_challenge.di

import com.joselpadi.uala_challenge.data.repository.CitiesRepositoryImpl
import com.joselpadi.uala_challenge.data.repository.WeatherDataRepositoryImpl
import com.joselpadi.uala_challenge.domain.repository.CitiesRepositoryInterface
import com.joselpadi.uala_challenge.domain.repository.WeatherDataRepositoryInterface
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCitiesRepository(
        impl: CitiesRepositoryImpl
    ): CitiesRepositoryInterface

    @Binds
    @Singleton
    abstract fun bindWeatherDataRepository(
        impl: WeatherDataRepositoryImpl
    ): WeatherDataRepositoryInterface

}