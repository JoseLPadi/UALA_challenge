package com.joselpadi.uala_challenge.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joselpadi.uala_challenge.R
import com.joselpadi.uala_challenge.domain.model.City
import com.joselpadi.uala_challenge.domain.model.Coord

@Composable
fun ItemCityWidget(city: City,
                   onCityFavorite: (City, Boolean) ->Unit,
                   onCitySelected: (City) -> Unit,
                   modifier:Modifier = Modifier){
    ItemCityWidgetContent(city, onCityFavorite, onCitySelected,modifier)
}
@Composable
private fun ItemCityWidgetContent(
    city: City,
    onCityFavorite: (City, Boolean) -> Unit,
    onCitySelected: (City) -> Unit,
    modifier: Modifier
) {
    var isFavorite by remember(city.id, city.isFavorite) {
        mutableStateOf(city.isFavorite)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCitySelected(city) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp, 8.dp)
            ) {
                Text(text = "${city.name} - ${city.id}")
                Text(text = "Lat=${city.coord.lat} - Lon=${city.coord.lon}")
            }
            IconToggleButton(
                checked = isFavorite,
                onCheckedChange = { newValue ->
                    isFavorite = newValue            // 👈 cambia instantáneamente
                    onCityFavorite(city, newValue)   // 👈 actualiza DB en background
                }
            ) {
                Icon(
                    painter = painterResource(
                        if (isFavorite)
                            R.drawable.favorite
                        else
                            R.drawable.unfavorite
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color.Yellow
                )
            }
        }
    }
}

@Composable
@Preview
private fun ItemCityPreview(){
    ItemCityWidget(City("country","name",22, Coord(1.1, 1.1),true), { _, _ ->},{})
}