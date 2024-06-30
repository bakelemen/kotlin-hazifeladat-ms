package hu.vanio.kotlin.hazifeladat.ms

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Response (
    val latitude:Double,
    val longitude:Double,
    val hourly: Measurements,
    val hourly_units: Unit
)