package com.joselpadi.uala_challenge.domain.mapper

import com.joselpadi.uala_challenge.data.local.entity.CityEntity
import com.joselpadi.uala_challenge.data.local.entity.CoordEntity
import com.joselpadi.uala_challenge.domain.model.City
import com.joselpadi.uala_challenge.domain.model.Coord

fun Coord.toEntity(): CoordEntity {
    return CoordEntity(
        lon = lon,
        lat = lat
    )
}

fun City.toEntity( isFavorite: Boolean): CityEntity {
    return CityEntity(
        id = id,
        country = country,
        name = name,
        coord = coord.toEntity(),
        isFavorite = isFavorite
    )
}