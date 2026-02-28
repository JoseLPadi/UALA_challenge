package com.joselpadi.uala_challenge.domain.model


class City (
    val country:String,
    val name: String,
    val id: Int,
    val coord: Coord,
    var isFavorite: Boolean =false)
