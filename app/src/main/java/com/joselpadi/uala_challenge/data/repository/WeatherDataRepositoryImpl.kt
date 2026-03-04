package com.joselpadi.uala_challenge.data.repository

import com.joselpadi.uala_challenge.data.remote.api.WeatherRetrofitInterface
import com.joselpadi.uala_challenge.data.remote.mapper.toEntity
import com.joselpadi.uala_challenge.domain.model.WeatherData
import com.joselpadi.uala_challenge.domain.repository.WeatherDataRepositoryInterface
import javax.inject.Inject

class WeatherDataRepositoryImpl @Inject constructor(
    private val api: WeatherRetrofitInterface): WeatherDataRepositoryInterface {
    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): WeatherData? {
        val response = api.getCurrentWeather(lat, lon)
        try {
            val weatherData = response.data[0].toEntity()
            return weatherData
        } catch (e: Exception) {
            return null
        }
    }
}