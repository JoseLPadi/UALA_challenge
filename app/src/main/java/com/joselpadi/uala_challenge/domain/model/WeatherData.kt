package com.joselpadi.uala_challenge.domain.model



data class WeatherData(
    val city_name: String,
    val country_code: String,
    val datetime: String,
    val temp: Double,
    val wind_spd: Double,
    val precip: Double,
    val clouds: Int,
    val weather: WeatherDescription
)

data class WeatherDescription(
    val description: String,
    val icon: String
)
