package com.joselpadi.uala_challenge.ui.detailcity

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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

@Composable
fun MapScreen(citySelected: City?, modifier: Modifier) {
    MapContent(citySelected, modifier)
}

@Composable
private fun MapContent(citySelected: City?,modifier: Modifier){
    if (citySelected!=null) {
        val coord = LatLng(citySelected.coord.lat, citySelected.coord.lon)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(coord, 12f) // Zoom adecuado para el marcador
        }
        cameraPositionState.position  = CameraPosition.fromLatLngZoom(
            coord,
            cameraPositionState.position.zoom
        )
        GoogleMap(
            modifier = modifier.padding(16.dp),
            cameraPositionState = cameraPositionState

        ) {
            Marker(state = MarkerState(position =  coord),
                title = citySelected.name)
        }
    }
}

@Composable
@Preview
private fun MapScreenPreview(){
    val city= City("RU", "Russian Federation", 2017370, Coord(100.0, 60.0))
    MapScreen(city,Modifier)
}