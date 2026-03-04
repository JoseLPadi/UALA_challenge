package com.joselpadi.uala_challenge.ui.navigation

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joselpadi.uala_challenge.di.DaggerAppComponent
import com.joselpadi.uala_challenge.ui.cities.CitiesViewModel
import com.joselpadi.uala_challenge.ui.detailcity.MapScreen
import com.joselpadi.uala_challenge.ui.detailcity.MapViewModel
import com.joselpadi.uala_challenge.ui.home.HomeScreen

@Composable
fun NavGraphComposable(isPortrait: Boolean, modifier:Modifier = Modifier){
    val navController = rememberNavController()
    val appComponent = DaggerAppComponent.factory()
        .create(LocalContext.current.applicationContext as Application)
    val factory = appComponent.viewModelFactory()
    val citiesViewModel: CitiesViewModel = viewModel(factory = factory)
    val mapViewModel: MapViewModel = viewModel(factory = factory)
    NavHost(navController = navController,
        modifier = modifier,
        startDestination = Destinations.HOME_SCREEN) {
        composable(route = Destinations.HOME_SCREEN) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(WindowInsets.statusBars.asPaddingValues())
                    .navigationBarsPadding()
            ) {
                HomeScreen(isPortrait,citiesViewModel, mapViewModel) { city ->

                    navController.navigate(Destinations.DETAIL_CITY_MAP_ROUTE)
                }
            }
        }

        composable(route = Destinations.DETAIL_CITY_MAP_ROUTE) {
            val city by mapViewModel.citySelected.collectAsState()
            val weatherData by mapViewModel.weatherData.collectAsState()
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(WindowInsets.statusBars.asPaddingValues())
                    .navigationBarsPadding()
            ) {
                MapScreen(city, weatherData, Modifier)
            }
        }
    }

}