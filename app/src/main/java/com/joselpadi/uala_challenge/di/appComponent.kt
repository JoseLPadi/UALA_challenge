package com.joselpadi.uala_challenge.di

import android.app.Application
import com.joselpadi.uala_challenge.data.local.dao.CityDAO
import com.joselpadi.uala_challenge.ui.cities.CitiesViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataBaseModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        WeatherUseCaseModule::class,
        ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
    fun viewModelFactory(): ViewModelFactory
    fun provideCitiesViewModel(): CitiesViewModel
    fun cityDao(): CityDAO

}