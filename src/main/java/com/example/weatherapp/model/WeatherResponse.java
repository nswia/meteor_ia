package com.example.weatherapp.model;

import java.util.List;

public class WeatherResponse {
    private Main main;
    private List<Weather> weather;

    public WeatherResponse() {}

    public WeatherResponse(String condition, String tempText) {
        this.main = new Main();
        this.main.setTemp(0);
        this.weather = List.of(new Weather(condition));
    }

    public double getTemp() {
        return main != null ? main.getTemp() : 0;
    }

    public String getCondition() {
        return (weather != null && !weather.isEmpty()) ? weather.get(0).getDescription() : "Sem dados";
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    // Subclasse para o campo "main"
    public static class Main {
        private double temp;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }

    // Subclasse para o campo "weather"
    public static class Weather {
        private String description;

        public Weather() {}

        public Weather(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
