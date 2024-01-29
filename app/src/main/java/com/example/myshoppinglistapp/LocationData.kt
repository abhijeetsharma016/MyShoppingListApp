package com.example.myshoppinglistapp

data class LocationData(
    val latitude:Double,
    val longitude: Double
)

data class GeocodingResult(
    val results: List<GeocodingResponse>,
    val status: String
)

data class GeocodingResponse(
    val formatted_address: String
)
