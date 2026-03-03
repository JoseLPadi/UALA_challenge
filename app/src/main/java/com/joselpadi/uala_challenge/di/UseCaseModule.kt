package com.joselpadi.uala_challenge.di

import com.joselpadi.uala_challenge.domain.repository.CitiesRepositoryInterface
import com.joselpadi.uala_challenge.domain.usecase.CitiesUseCase
import com.joselpadi.uala_challenge.domain.usecase.InitializeCitiesUseCase
import com.joselpadi.uala_challenge.domain.usecase.LoadCitiesUseCase
import com.joselpadi.uala_challenge.domain.usecase.LoadFavoriteCitiesUseCase
import com.joselpadi.uala_challenge.domain.usecase.SearchCitiesUseCase
import com.joselpadi.uala_challenge.domain.usecase.SetCityAsFavorite
import com.joselpadi.uala_challenge.domain.usecase.SetCityAsUnfavorite
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    fun provideLoadCitiesUseCase(repository: CitiesRepositoryInterface): LoadCitiesUseCase {
        return LoadCitiesUseCase(repository)
    }

    @Provides
    fun provideInitializeCitiesUseCase(repository: CitiesRepositoryInterface): InitializeCitiesUseCase {
        return InitializeCitiesUseCase(repository)
    }

    @Provides
    fun provideSetCityAsFavorite(repository: CitiesRepositoryInterface): SetCityAsFavorite {
        return SetCityAsFavorite(repository)
    }

    @Provides
    fun provideSearchCitiesUseCase(): SearchCitiesUseCase {
        return SearchCitiesUseCase()
    }

    @Provides
    @Singleton
    fun provideCitiesUseCase(
        loadCities: LoadCitiesUseCase,
        loadFavoriteCities: LoadFavoriteCitiesUseCase,
        initializeCities: InitializeCitiesUseCase,
        setFavorite: SetCityAsFavorite,
        setUnfavorite: SetCityAsUnfavorite,
        searchCities: SearchCitiesUseCase

    ): CitiesUseCase {
        return CitiesUseCase(
            loadCitiesUseCase = loadCities,
            initializeCitiesUseCase = initializeCities,
            setCityAsFavoriteUseCase = setFavorite,
            setCityAsUnfavoriteUseCase = setUnfavorite,
            searchCitiesUseCase = searchCities,
            getFavoriteCitiesUseCase = loadFavoriteCities
        )
    }
}