package com.joselpadi.uala_challenge.di

import com.joselpadi.uala_challenge.domain.repository.WeatherDataRepositoryInterface
import com.joselpadi.uala_challenge.domain.usecase.GetWeatherUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WeatherUseCaseModule {
    @Provides
    @Singleton
    fun provideWeatherUseCase(repository: WeatherDataRepositoryInterface): GetWeatherUseCase {
        return GetWeatherUseCase(repository)
    }
}