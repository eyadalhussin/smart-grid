package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.model.WeatherModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private final String apiKey = "697cbcb87d9cc74acc9a0987026f06f1";
    private final String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";
    private final String historicalApiUrl = "https://api.openweathermap.org/data/2.5/onecall/timemachine?lat={latitude}&lon={longitude}&dt={timestamp}&appid={apiKey}";

    public WeatherModel getWeatherByCity(String city) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherModel> response = restTemplate.getForEntity(apiUrl, WeatherModel.class, city, apiKey);
        return response.getBody();
    }

    public WeatherModel getHistoricalWeatherByCity(String city, long timestamp) {
        WeatherModel currentWeather = getWeatherByCity(city);
        double latitude = currentWeather.getCoord().getLat();
        double longitude = currentWeather.getCoord().getLon();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherModel> response = restTemplate.getForEntity(historicalApiUrl, WeatherModel.class, latitude, longitude, timestamp, apiKey);
        return response.getBody();
    }
}


