package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.Observer.TimeObserver;
import de.fhdo.SmartGrid.Observer.WeatherObserver;
import de.fhdo.SmartGrid.model.WeatherModel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService implements TimeObserver {

    private final TimeSimulationService timeSimulationComponent;
    private List<WeatherObserver> weatherObservers = new ArrayList<>();

    private WeatherModel currentWeather;

    @Autowired
    public WeatherService(TimeSimulationService timeSimulationComponent) {
        this.timeSimulationComponent = timeSimulationComponent;
    }

    private Instant lastWeatherUpdate = null;

    @PostConstruct
    public void init() {
        timeSimulationComponent.registerObserver(this);
        setLocalWeather();
    }

    public void registerObserver(WeatherObserver weatherObserver) {
        weatherObservers.add(weatherObserver);
    }

    public void removeObserver(WeatherObserver weatherObserver) {
        weatherObservers.remove(weatherObserver);
    }

    public WeatherModel getCurrentWeather() {
        return currentWeather;
    }


    private void setLocalWeather() {
        RestTemplate restTemplate = new RestTemplate();
        String weatherApiUrl = "https://icecreamparty.de/api/wetterdaten/now";
        ResponseEntity<WeatherModel> response = restTemplate.getForEntity(weatherApiUrl, WeatherModel.class);
        currentWeather = response.getBody();
    }

    @Override
    public void timeUpdated() {
        if (lastWeatherUpdate == null) {
            lastWeatherUpdate = timeSimulationComponent.getCurrentTime();
            return;
        }

        if (timeSimulationComponent.getCurrentTime().getEpochSecond() - lastWeatherUpdate.getEpochSecond() >= 60) {
            lastWeatherUpdate = timeSimulationComponent.getCurrentTime();
            setLocalWeather();
        }
    }
}


