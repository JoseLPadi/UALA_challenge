package com.joselpadi.uala_challenge.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.joselpadi.uala_challenge.domain.model.City
import com.joselpadi.uala_challenge.ui.cities.CitiesScreen
import com.joselpadi.uala_challenge.ui.cities.CitiesViewModel
import com.joselpadi.uala_challenge.ui.detailcity.MapScreen
import com.joselpadi.uala_challenge.ui.detailcity.MapViewModel

@Composable
fun HomeScreen(isPortrait:Boolean,listCityViewModel: CitiesViewModel, mapViewModel: MapViewModel, onCitySelectedChangeScreen: (City) -> Unit){
    HomeContent(isPortrait,listCityViewModel,  mapViewModel, onCitySelectedChangeScreen)
}

@Composable
private fun HomeContent(
    isPortrait: Boolean,
    listCitiesviewModel: CitiesViewModel,
    mapViewModel: MapViewModel,
    onCitySelectedChangeScreen: (City) -> Unit
) {
    val cityList by listCitiesviewModel.listCities.collectAsState()
    val citySelected by mapViewModel.citySelected.collectAsState()
    val weatherData by mapViewModel.weatherData.collectAsState()
    val weight = if(isPortrait) 1f else 0.6f
    Row (modifier = Modifier.fillMaxSize()){
        Box(modifier=Modifier.weight(weight)) {
            CitiesScreen(
                cityList,
                { listCitiesviewModel.onFilter(it) },
                { city ->
                    mapViewModel.updateCity(city)
                    if (isPortrait) {
                        onCitySelectedChangeScreen(city)
                    }
                },
                { city, favorite -> listCitiesviewModel.onCityFavorite(city, favorite) },
                { listCitiesviewModel.onShowFavorites(it) },
                listCitiesviewModel.loadingDialog.collectAsState(),
                modifier = Modifier
            )
        }
        if (!isPortrait) {
            Box(modifier = Modifier.weight(0.4f)) {
                MapScreen(citySelected, weatherData,  modifier = Modifier)
            }
        }
    }

}
