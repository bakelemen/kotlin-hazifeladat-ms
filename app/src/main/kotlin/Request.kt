package hu.vanio.kotlin.hazifeladat.ms

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate
import java.util.stream.Collectors

@JsonIgnoreProperties(ignoreUnknown = true)
data class Request (
    val latitude:Double,
    val longitude:Double,
    val hourly: Measurements,
    val hourly_units: Unit
)

fun Request.calculateMidTemp(): Map<LocalDate, String> {

    return hourly.time.stream().map { date -> Temp(date.toLocalDate(), hourly.temperature_2m[hourly.time.indexOf(date)]) }
        .collect(Collectors.toList()).groupBy { it.date }.mapValues { (_, temps) -> String.format("%4.2f", temps.sumOf { it.temp } / temps.size) }

}