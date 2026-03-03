package com.joselpadi.uala_challenge.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joselpadi.uala_challenge.data.local.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface  CityDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Query("SELECT * FROM cities ORDER BY name COLLATE NOCASE ASC")
    fun getAllCitiess(): Flow<List<CityEntity>>

    @Delete
    suspend fun deleteCity(city: CityEntity)

    @Query("DELETE FROM cities")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities: List<CityEntity>)

    @Query("SELECT * FROM cities WHERE isFavorite = 1 ORDER BY name COLLATE NOCASE ASC")
    fun getFavoriteCities(): Flow<List<CityEntity>>

}