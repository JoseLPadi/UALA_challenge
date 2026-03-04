package com.joselpadi.uala_challenge.data.remote.api
import com.joselpadi.uala_challenge.BuildConfig
import com.joselpadi.uala_challenge.data.remote.dto.WeatherResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query
interface WeatherRetrofitInterface {

    @GET("current")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY
    ): WeatherResponseDTO

}