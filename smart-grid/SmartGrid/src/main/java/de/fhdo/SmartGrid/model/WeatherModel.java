package de.fhdo.SmartGrid.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherModel {
    @JsonProperty("weather")
    private List<WeatherInfo> weather;

    @JsonProperty("main")
    private MainInfo main;

    public List<WeatherInfo> getWeather() {
        return weather;
    }

    @JsonProperty("coord")
    private CoordInfo coord;

    // ...
    public CoordInfo getCoord() {
        return coord;
    }

    public void setCoord(CoordInfo coord) {
        this.coord = coord;
    }


    public void setWeather(List<WeatherInfo> weather) {
        this.weather = weather;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherInfo {
        @JsonProperty("main")
        private String main;

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MainInfo {
        @JsonProperty("temp")
        private double temp;

        @JsonProperty("wind_speed")
        private double windSpeed;

        public double getTemp() {
            return temp;
        }
        public void setTemp(double temp) {
            this.temp = temp;
        }
        public double getWindSpeed() {
            return windSpeed;
        }
        public void setWindSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CoordInfo {
        @JsonProperty("lat")
        private double lat;

        @JsonProperty("lon")
        private double lon;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }
}

