package com.joselpadi.uala_challenge.data.remote.mapper

import com.joselpadi.uala_challenge.data.local.entity.CityEntity
import com.joselpadi.uala_challenge.data.local.entity.CoordEntity
import com.joselpadi.uala_challenge.data.remote.dto.CityDTO
import com.joselpadi.uala_challenge.data.remote.dto.CoordDTO

fun CoordDTO.toEntity(): CoordEntity {
    return CoordEntity(
        lon = lon,
        lat = lat
    )
}

fun CityDTO.toEntity(): CityEntity {
    return CityEntity(
        id = _id,
        country = country,
        name = name,
        coord = coord.toEntity(),
        isFavorite = false
    )
}

fun List<CityDTO>.toEntityList(): List<CityEntity> {
    return map { it.toEntity() }
}