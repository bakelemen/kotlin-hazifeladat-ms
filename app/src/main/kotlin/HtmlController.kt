package hu.vanio.kotlin.hazifeladat.ms

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController(val tempService: TempService) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Napi középhőmérséklet"
        val temp = tempService.getTemp()
        model["temp"] = temp.calculateMidTemp()
        model["lat"] = temp.latitude
        model["lng"] = temp.longitude
        model["unit"] = temp.hourly_units.temperature_2m
        return "midtemp"
    }

}