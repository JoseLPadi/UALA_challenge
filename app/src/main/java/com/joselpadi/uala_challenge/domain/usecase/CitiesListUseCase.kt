package com.joselpadi.uala_challenge.domain.usecase

import com.joselpadi.uala_challenge.domain.logic.SearchService
import com.joselpadi.uala_challenge.domain.model.City
import com.joselpadi.uala_challenge.domain.repository.CitiesRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class CitiesUseCase(
    val loadCitiesUseCase: LoadCitiesUseCase,
    val initializeCitiesUseCase: InitializeCitiesUseCase,
    val setCityAsFavoriteUseCase: SetCityAsFavorite,
    val setCityAsUnfavoriteUseCase: SetCityAsUnfavorite,
    val searchCitiesUseCase: SearchCitiesUseCase,
    val getFavoriteCitiesUseCase: LoadFavoriteCitiesUseCase
)

class LoadCitiesUseCase@Inject constructor(
    private val repository: CitiesRepositoryInterface
) {
    operator fun invoke(): Flow<List<City>> {
        return repository.getCities()
    }
}
class LoadFavoriteCitiesUseCase  @Inject constructor(
    private val repository: CitiesRepositoryInterface
) {
    operator fun invoke(): Flow<List<City>> {
        return repository.getFavoriteCities()
    }
}
class InitializeCitiesUseCase @Inject constructor(
        private val repository: CitiesRepositoryInterface,
    ) {
    suspend operator fun invoke(): Flow<List<City>> {
        repository.initialize()
        return repository.getCities()
    }
}

class SetCityAsFavorite @Inject constructor(
    private val repository: CitiesRepositoryInterface
){
    suspend operator fun invoke(city: City) =
    repository.setCityAsFavorite(city, true)
}

class SetCityAsUnfavorite @Inject constructor(
    private val repository: CitiesRepositoryInterface){
    suspend operator fun invoke(city: City) =
        repository.setCityAsFavorite(city, false)
}


class SearchCitiesUseCase @Inject constructor() {

    operator fun invoke(textFilter: String, cities: List<City>): Pair<Int, Int> {
        return  SearchService.filterList(textFilter, cities) ?: Pair(0, 0)

    }
}


