package hu.vanio.kotlin.hazifeladat.ms

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service
import java.net.URI


@Service
class TempService {

    fun getTemp(): Request {
        val json = URI("https://api.open-meteo.com/v1/forecast?latitude=47.4984&longitude=19.0404&hourly=temperature_2m&timezone=auto").toURL().readText()
        return jacksonObjectMapper().registerModule(JavaTimeModule()).readValue(json)
    }

}