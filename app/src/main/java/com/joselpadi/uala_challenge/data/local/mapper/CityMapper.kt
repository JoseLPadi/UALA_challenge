package com.joselpadi.uala_challenge.data.local.mapper

import com.joselpadi.uala_challenge.data.local.entity.CityEntity
import com.joselpadi.uala_challenge.data.local.entity.CoordEntity
import com.joselpadi.uala_challenge.domain.model.City
import com.joselpadi.uala_challenge.domain.model.Coord

fun CoordEntity.toDomain(): Coord {
    return Coord(
        lon = lon,
        lat = lat
    )
}
fun CityEntity.toDomain(): City {
    return City(
        id = id,
        country = country,
        name = name,
        coord = coord.toDomain(),
        isFavorite = isFavorite
    )
}

fun List<CityEntity>.toDomainList(): List<City> {
    return map { it.toDomain() }
}
