package com.joselpadi.uala_challenge.data.remote.dto

data class CityDTO(val country:String, val name: String, val _id: Int, val coord: CoordDTO, var isFavorite: Boolean =false)
