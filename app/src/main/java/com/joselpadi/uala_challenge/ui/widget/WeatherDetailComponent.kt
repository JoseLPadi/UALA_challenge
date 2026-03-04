package com.joselpadi.uala_challenge.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.joselpadi.uala_challenge.domain.model.WeatherData
import com.joselpadi.uala_challenge.domain.model.WeatherDescription


@Composable
fun WeatherDetailComponent(weather: WeatherData, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            AsyncImage(
                model = "https://cdn.weatherbit.io/static/img/icons/${weather.weather.icon}.png",
                contentDescription = weather.weather.description,
                modifier = Modifier.size(64.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = "${weather.city_name}, ${weather.country_code}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Hora: ${weather.datetime}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Temperatura: ${weather.temp}°C", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Viento: ${weather.wind_spd} m/s", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Lluvia: ${weather.precip} mm", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Nubes: ${weather.clouds}% - ${weather.weather.description}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherDetailComponent() {
    val sampleWeather = WeatherData(
        city_name = "Raleigh",
        country_code = "US",
        datetime = "2026-03-03:19",
        temp = 12.6,
        wind_spd = 0.0,
        precip = 0.0,
        clouds = 75,
        weather = WeatherDescription("Broken clouds", "c03d")
    )
    WeatherDetailComponent(weather = sampleWeather)
}