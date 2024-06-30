package hu.vanio.kotlin.hazifeladat.ms

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.Unit
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringBootTest(classes = [TempService::class])
class WeatherAppTest(@Autowired private val tempService: TempService) {

    @Test fun `sikeres lekerdezes`(): Unit {

        val response = tempService.getDeserializedResponse()
        assertNotNull(response)
        assertNotNull(response.latitude)
        assertNotNull(response.longitude)
        assertNotNull(response.hourly_units.temperature_2m)
        assertNotNull(response.hourly_units.time)
        assertTrue (response.hourly.time.isNotEmpty())
        assertTrue (response.hourly.temperature_2m.isNotEmpty())
        assertEquals(response.hourly.time.size, response.hourly.temperature_2m.size)

        val calculated = tempService.calculateMidTemp(response)
        assertTrue(calculated.isNotEmpty())
        assertTrue(calculated.size == 7)
    }

    @Test fun `bad response`(): Unit {

        val badJson = "Bad Request 400"
        val deserialized = tempService.deserialize(badJson)
        assertNull(deserialized)
        val calculated = tempService.calculateMidTemp(deserialized)
        assertTrue(calculated.isEmpty())
    }
}