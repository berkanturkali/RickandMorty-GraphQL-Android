package com.android.example.rickandmortygraphql.model

data class Locations(
    val results: List<Location>,
)

data class Location(
    val id: String,
    val name: String,
    val type: String,
)