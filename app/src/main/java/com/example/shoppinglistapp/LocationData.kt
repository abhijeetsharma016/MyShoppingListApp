package com.example.shoppinglistapp

data class LocationData(
    val latitud: Double,
    val longitude: Double
)

data class GeocodingResponse(
    val results: List<GeocodingResult>,
    val status: String
)

data class GeocodingResult(
val formatted_address: String
)