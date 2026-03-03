package com.joselpadi.uala_challenge.di

import android.app.Application
import androidx.room.Room
import com.joselpadi.uala_challenge.data.local.dao.CityDAO
import com.joselpadi.uala_challenge.data.local.database.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDataBase {
        return Room.databaseBuilder(
            application,
            AppDataBase::class.java,
            "cities_db"
        ).build()
    }

    @Provides
    fun provideCityDao(database: AppDataBase): CityDAO {
        return database.cityDao()
    }
}