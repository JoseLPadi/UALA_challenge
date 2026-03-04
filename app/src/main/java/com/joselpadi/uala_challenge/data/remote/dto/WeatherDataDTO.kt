package com.joselpadi.uala_challenge.data.remote.dto


data class WeatherResponseDTO(
    val count: Int,
    val data: List<WeatherDataDTO>
)
data class WeatherDataDTO(
    val city_name: String,
    val country_code: String,
    val datetime: String,
    val temp: Double,
    val wind_spd: Double,
    val precip: Double,
    val clouds: Int,
    val weather: WeatherDescriptionDTO
)

data class WeatherDescriptionDTO(
    val description: String,
    val icon: String
)
