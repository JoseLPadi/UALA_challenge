package com.joselpadi.uala_challenge.ui.detailcity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.joselpadi.uala_challenge.domain.model.City
import com.joselpadi.uala_challenge.domain.model.Coord
import com.joselpadi.uala_challenge.domain.model.WeatherData
import com.joselpadi.uala_challenge.ui.widget.WeatherDetailComponent

@Composable
fun MapScreen(citySelected: City?, weatherData: WeatherData?,  modifier: Modifier) {
    MapContent(citySelected, weatherData, modifier)
}
@Composable
private fun MapContent(
    citySelected: City?,
    weatherData: WeatherData?,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        if (citySelected != null) {

            val coord = LatLng(
                citySelected.coord.lat,
                citySelected.coord.lon
            )

            val cameraPositionState = rememberCameraPositionState()

            LaunchedEffect(coord) {
                cameraPositionState.position =
                    CameraPosition.fromLatLngZoom(coord, 12f)
            }

            GoogleMap(
                modifier = Modifier
                    .weight(1f)   // 🔥 CLAVE
                    .fillMaxWidth(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = coord),
                    title = citySelected.name
                )
            }
        }

        if (weatherData != null) {
            WeatherDetailComponent(
                weatherData,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
        }
    }
}

@Composable
@Preview
private fun MapScreenPreview(){
    val city= City("RU", "Russian Federation", 2017370, Coord(100.0, 60.0))
    MapScreen(city, null, Modifier)
}