package hu.vanio.kotlin.hazifeladat.ms

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service
import java.net.URI
import java.time.LocalDate
import java.util.stream.Collectors


@Service
class TempService {

    private fun getResponse(): String {
        return URI("https://api.open-meteo.com/v1/forecast?latitude=47.4984&longitude=19.0404&hourly=temperature_2m&timezone=auto").toURL().readText()
    }

    fun deserialize(json: String): Response? {
        return try {
            jacksonObjectMapper().registerModule(JavaTimeModule()).readValue(json)
        } catch (e: Exception) {
            null
        }
    }

    fun calculateMidTemp(response: Response?): Map<LocalDate, String> {
        if(response == null) {
            return mapOf()
        }
        return response.hourly.time.stream().map { date -> Temp(date.toLocalDate(), response.hourly.temperature_2m[response.hourly.time.indexOf(date)]) }
            .collect(Collectors.toList()).groupBy { it.date }.mapValues { (_, temps) -> String.format("%4.2f", temps.sumOf { it.temp } / temps.size) }
    }
    fun getDeserializedResponse(): Response? {
        return deserialize(getResponse())
    }

}