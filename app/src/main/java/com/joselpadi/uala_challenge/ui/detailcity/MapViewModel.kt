package com.joselpadi.uala_challenge.ui.detailcity

import com.joselpadi.uala_challenge.domain.model.City
import com.joselpadi.uala_challenge.ui.core.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MapViewModel  @Inject constructor() : BaseViewModel() {

    private val _citySelected = MutableStateFlow<City?>(null)
    val citySelected: StateFlow<City?> = _citySelected.asStateFlow()

    fun updateCity(city:City){
        _citySelected.value = city
    }

}