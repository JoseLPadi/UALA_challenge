package com.joselpadi.uala_challenge.domain.repository

import com.joselpadi.uala_challenge.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CitiesRepositoryInterface {
    suspend fun initialize()
    fun getCities(): Flow<List<City>>
    suspend fun setCityAsFavorite(city: City, isFavorite: Boolean)

    fun getFavoriteCities(): Flow<List<City>>
}
