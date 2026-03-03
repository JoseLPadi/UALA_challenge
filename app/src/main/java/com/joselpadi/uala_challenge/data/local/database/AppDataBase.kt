package com.joselpadi.uala_challenge.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joselpadi.uala_challenge.data.local.dao.CityDAO
import com.joselpadi.uala_challenge.data.local.entity.CityEntity

@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun cityDao(): CityDAO
}