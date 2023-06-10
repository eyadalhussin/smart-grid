package de.fhdo.SmartGrid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherModel {

    @JsonProperty("id")
    private double id;

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("tempMin")
    private double tempMin;

    @JsonProperty("tempMax")
    private double tempMax;

    @JsonProperty("feelsLike")
    private double feelsLike;

    @JsonProperty("pressure")
    private int pressure;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("dewPoint")
    private double dewPoint;

    @JsonProperty("cloud")
    private int cloud;

    @JsonProperty("weatherId")
    private int weatherId;

    @JsonProperty("weatherName")
    private String weatherName;

    @JsonProperty("weatherDesc")
    private String weatherDesc;

    @JsonProperty("weatherIcon")
    private String weatherIcon;

    @JsonProperty("visibility")
    private int visibility;

    @JsonProperty("windSpeed")
    private double windSpeed;

    @JsonProperty("windDeg")
    private int windDeg;

    @JsonProperty("windGust")
    private double windGust;

    @JsonProperty("rain1h")
    private Double rain1h;

    @JsonProperty("snow1h")
    private Double snow1h;

    public double getId() {
        return id;
    }

    public double getTemp() {
        return temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public int getCloud() {
        return cloud;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public String getWeatherName() {
        return weatherName;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public int getVisibility() {
        return visibility;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public double getWindGust() {
        return windGust;
    }

    public Double getRain1h() {
        return rain1h;
    }

    public Double getSnow1h() {
        return snow1h;
    }
}
