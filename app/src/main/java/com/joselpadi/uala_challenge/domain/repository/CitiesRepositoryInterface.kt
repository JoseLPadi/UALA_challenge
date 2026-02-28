package com.joselpadi.uala_challenge.domain.repository

import com.joselpadi.uala_challenge.domain.model.City

interface CitiesRepositoryInterface {
    suspend fun initialize()
    fun getCities(): List<City>
}