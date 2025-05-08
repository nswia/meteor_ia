package com.example.weatherapp.service;

import com.example.weatherapp.model.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String WEATHER_API_URL =
            "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric&lang=pt";

    public WeatherResponse getWeatherData(String city, String apiKey) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(WEATHER_API_URL, WeatherResponse.class, city, apiKey);
        } catch (Exception e) {
            System.err.println("Erro ao buscar dados da API: " + e.getMessage());
            return new WeatherResponse("Erro ao buscar dados do clima", "");
        }
    }
}
