package com.example.weatherapp.controller;

import com.example.weatherapp.model.WeatherResponse;
import com.example.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherRestController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String getWeather(@RequestParam(name = "city", required = false, defaultValue = "São Paulo") String city, Model model) {
        WeatherResponse weatherData = weatherService.getWeatherData(city, "d1bb38bf24f0f2dd8216c9c1cf3770d4"); // Substitua YOUR_API_KEY pela sua chave
        model.addAttribute("weatherData", weatherData);
        model.addAttribute("city", city);
        return "weather";
    }
}
