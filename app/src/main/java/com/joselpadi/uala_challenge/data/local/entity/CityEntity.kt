package com.joselpadi.uala_challenge.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_cities")
data class CityEntity(
    @PrimaryKey val id: Int,
    val country:String,
    val name: String,
    @Embedded val coord: CoordEntity,
    var isFavorite: Boolean =false)
