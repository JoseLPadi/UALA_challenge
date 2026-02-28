package com.joselpadi.uala_challenge.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface  CityDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityDAO)

    @Query("SELECT * FROM favorite_cities ORDER BY name COLLATE NOCASE ASC")
    suspend fun getAllCitiess(): List<CityDAO>

    @Delete
    suspend fun deleteCity(city: CityDAO)
}