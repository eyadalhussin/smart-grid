package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.model.WeatherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    // deprecated
    private final String apiKey = "697cbcb87d9cc74acc9a0987026f06f1";
    private final String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";

    ///////////////////////////////////
    private RestTemplate restTemplate;
    private final String weatherApiUrl = "http://159.89.104.105:8080/api/wetterdaten/now";


    public WeatherModel getWeatherByCity(String city) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherModel> response = restTemplate.getForEntity(apiUrl, WeatherModel.class, city, apiKey);
        return response.getBody();
    }


    public WeatherModel getLocalWeather(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherModel> response = restTemplate.getForEntity(weatherApiUrl, WeatherModel.class);
        return response.getBody();
    }
}


