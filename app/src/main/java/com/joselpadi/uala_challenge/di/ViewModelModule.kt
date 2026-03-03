package com.joselpadi.uala_challenge.di

import androidx.lifecycle.ViewModel
import com.joselpadi.uala_challenge.ui.cities.CitiesViewModel
import com.joselpadi.uala_challenge.ui.detailcity.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CitiesViewModel::class)
    abstract fun bindCitiesViewModel(
        viewModel: CitiesViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(
        viewModel: MapViewModel
    ): ViewModel
}