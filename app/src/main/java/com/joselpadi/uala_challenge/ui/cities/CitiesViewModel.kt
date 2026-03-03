package com.joselpadi.uala_challenge.ui.cities

import androidx.lifecycle.viewModelScope
import com.joselpadi.uala_challenge.ui.core.BaseViewModel
import com.joselpadi.uala_challenge.domain.model.City
import com.joselpadi.uala_challenge.domain.usecase.CitiesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CitiesViewModel @Inject constructor(
    private val citiesUseCase: CitiesUseCase) : BaseViewModel() {
    private val filterText = MutableStateFlow("")
    private val showingFavorites = MutableStateFlow(false)

    val listCities: StateFlow<List<City>> =
        combine(
            citiesUseCase.loadCitiesUseCase(),          // Flow<List<City>>
            citiesUseCase.getFavoriteCitiesUseCase(),  // Flow<List<City>>
            filterText,
            showingFavorites
        ) { cities, favoriteCities, filter, showFavorites ->
            val baseList = if (showFavorites) favoriteCities else cities
            val (start, end) = citiesUseCase.searchCitiesUseCase(filterText.value, baseList)
            if (end >= start) {
                baseList.subList(start, (end) + 1)
            } else {
                emptyList()
            }

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(30),
            emptyList()
        )
    init {
        viewModelScope.launch {
            _loadingDialog.value=true
            withContext(Dispatchers.IO) {
                citiesUseCase.initializeCitiesUseCase()
            }
            _loadingDialog.value=false
        }
    }

    fun onFilter(textFilter: String) {
        filterText.value = textFilter
    }

    fun onCityFavorite(city: City, favorite: Boolean) {
        city.isFavorite = favorite
        viewModelScope.launch {
            if (favorite)
                citiesUseCase.setCityAsFavoriteUseCase(city)
            else
                citiesUseCase.setCityAsUnfavoriteUseCase(city)
        }
    }

    fun onShowFavorites(showFavorites: Boolean) {
        showingFavorites.value = showFavorites
    }
}