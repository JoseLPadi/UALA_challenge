package com.joselpadi.uala_challenge.data.repository

import com.joselpadi.uala_challenge.data.local.dao.CityDAO
import com.joselpadi.uala_challenge.data.local.entity.CityEntity
import com.joselpadi.uala_challenge.data.local.mapper.toDomainList
import com.joselpadi.uala_challenge.data.remote.api.CitiesRetrofitInterfeace
import com.joselpadi.uala_challenge.data.remote.mapper.toEntityList
import com.joselpadi.uala_challenge.domain.mapper.toEntity
import com.joselpadi.uala_challenge.domain.model.City
import com.joselpadi.uala_challenge.domain.repository.CitiesRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.map

@Singleton
class CitiesRepositoryImpl  @Inject constructor(
    private val api: CitiesRetrofitInterfeace,
    private val dao: CityDAO
): CitiesRepositoryInterface  {

    override suspend fun initialize() {
        val localCities = dao.getAllCitiess().first().toDomainList()
        val remoteCities = api.getCities().toEntityList()
        val merged = mergeList(remoteCities, localCities)
        dao.clearAll()
        dao.insertAll(merged)
    }

    private fun mergeList(remoteCities: List<CityEntity>, localCities: List<City>): List<CityEntity> {
        val localMap = localCities.associateBy { it.id }
        return remoteCities.map { remote ->
            val local = localMap[remote.id]
            if (local != null) {
                remote.copy(isFavorite = local.isFavorite)
            } else {
                remote.copy(isFavorite = false)
            }
        }
    }

    override fun getCities(): Flow<List<City>> = dao.getAllCitiess().map { it.toDomainList() }

    override suspend fun setCityAsFavorite(city: City, isFavorite: Boolean) {
        val cityEntity = city.toEntity(isFavorite)
        dao.insertCity(cityEntity)
    }

    override fun getFavoriteCities(): Flow<List<City>> {
        return dao.getFavoriteCities().map { it.toDomainList() }
    }
}