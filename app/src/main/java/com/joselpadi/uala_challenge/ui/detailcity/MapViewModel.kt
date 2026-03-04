package com.joselpadi.uala_challenge.ui.detailcity

import androidx.lifecycle.viewModelScope
import com.joselpadi.uala_challenge.domain.model.City
import com.joselpadi.uala_challenge.domain.model.WeatherData
import com.joselpadi.uala_challenge.domain.usecase.GetWeatherUseCase
import com.joselpadi.uala_challenge.ui.core.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel  @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : BaseViewModel() {

    private val _citySelected = MutableStateFlow<City?>(null)
    val citySelected: StateFlow<City?> = _citySelected.asStateFlow()
    private val _weatherData = MutableStateFlow<WeatherData?>(null)
    val weatherData: StateFlow<WeatherData?> = _weatherData

    fun updateCity(city:City){
        _citySelected.value = city
        getWeather(city.coord.lat, city.coord.lon)
    }

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = getWeatherUseCase(
                    lat = lat,
                    lon = lon,
                )
                if (response!=null)
                    _weatherData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}