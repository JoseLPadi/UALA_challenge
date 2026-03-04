package com.joselpadi.uala_challenge.domain.usecase

import com.joselpadi.uala_challenge.domain.model.WeatherData
import com.joselpadi.uala_challenge.domain.repository.WeatherDataRepositoryInterface
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherDataRepositoryInterface,
) {
    suspend operator fun invoke(lat: Double, lon: Double): WeatherData? {
        return repository.getCurrentWeather(lat = lat, lon= lon)
    }
}