package com.joselpadi.uala_challenge.data.remote.mapper

import com.joselpadi.uala_challenge.data.remote.dto.WeatherDataDTO
import com.joselpadi.uala_challenge.data.remote.dto.WeatherDescriptionDTO
import com.joselpadi.uala_challenge.domain.model.WeatherData
import com.joselpadi.uala_challenge.domain.model.WeatherDescription

fun WeatherDataDTO.toEntity(): WeatherData {
    return WeatherData(
        city_name = city_name,
        country_code = country_code,
        datetime = datetime,
        temp = temp,
        wind_spd = wind_spd,
        precip = precip,
        clouds = clouds,
        weather = weather.toEntity()
    )
}

fun WeatherDescriptionDTO.toEntity(): WeatherDescription {
    return WeatherDescription(
        description = description,
        icon = icon
    )
}