package com.joselpadi.uala_challenge.domain.repository

import com.joselpadi.uala_challenge.domain.model.WeatherData

interface WeatherDataRepositoryInterface {

    suspend fun getCurrentWeather(lat: Double, lon: Double): WeatherData?
}