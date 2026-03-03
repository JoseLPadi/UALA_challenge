package com.joselpadi.uala_challenge.ui.cities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import com.joselpadi.uala_challenge.ui.widget.FilterWidget
import com.joselpadi.uala_challenge.ui.widget.ItemCityWidget
import com.joselpadi.uala_challenge.ui.widget.LoadingDialog

@Composable
fun CitiesScreen(
    list: List<City>,
    onFilter: (String) -> Unit,
    onCitySelected: (City) -> Unit,
    onCityFavorite: (City, Boolean) -> Unit,
    onShowFavorites: (Boolean) -> Unit,
    loadingAlert: State<Boolean>,
    modifier: Modifier
){
    ListCitiesContent(list, onFilter, onCitySelected, onCityFavorite, onShowFavorites, loadingAlert, modifier)
}

@Composable
private fun ListCitiesContent(
    list: List<City>,
    onFilter: (String) -> Unit,
    onCitySelected: (City) -> Unit,
    onCityFavorite: (City, Boolean) -> Unit,
    onShowFavorites: (Boolean) -> Unit,
    loadingAlert: State<Boolean>,
    modifier: Modifier
){
    var showFavorite by remember { mutableStateOf(false) }
    Column(modifier = modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Show Favorites",
                modifier = Modifier.padding(8.dp)
            )
            IconToggleButton(
                checked = showFavorite,
                onCheckedChange = { onShowFavorites(!showFavorite)
                    showFavorite = !showFavorite
                }) {
                Icon(
                    painter = if (showFavorite) painterResource(R.drawable.favorite) else painterResource(
                        R.drawable.unfavorite
                    ),
                    contentDescription = "",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Yellow
                )
            }
        }
        FilterWidget(onFilter)
        LazyColumn {
            items(list.size) { city ->
                ItemCityWidget(city= list[city],
                    onCityFavorite,
                    onCitySelected)
            } }
        if (loadingAlert.value) {
            LoadingDialog { true }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListCitiesPreview() {
    val testCities = listOf(
        City("RU", "Yurevichi", 803611, Coord(39.934444, 43.600555)),
        City("GE", "Gumist’a", 614371, Coord(40.973888, 43.026943), true),
        City("GE", "Ptitsefabrika", 874560, Coord(40.290558, 43.183613), true),
        City("GE", "Orekhovo", 874652, Coord(40.146111, 43.351391))
    )
    val loadingState = remember { mutableStateOf(true) }
    CitiesScreen(
        list = testCities,
        onFilter = {},
        onCitySelected = {},
        onCityFavorite = { _, _ -> },
        onShowFavorites = {},
        loadingAlert = loadingState,
        modifier = Modifier.fillMaxSize()
    )
}