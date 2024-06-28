package hu.vanio.kotlin.hazifeladat.ms
import java.time.LocalDateTime

data class Measurements (
    val time: List<LocalDateTime>,
    val temperature_2m: List<Double>
)