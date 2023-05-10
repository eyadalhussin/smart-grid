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
    private double rain1h;

    @JsonProperty("snow1h")
    private Object snow1h;

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getWeatherName() {
        return weatherName;
    }

    public void setWeatherName(String weatherName) {
        this.weatherName = weatherName;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }

    public double getWindGust() {
        return windGust;
    }

    public void setWindGust(double windGust) {
        this.windGust = windGust;
    }

    public double getRain1h() {
        return rain1h;
    }

    public void setRain1h(double rain1h) {
        this.rain1h = rain1h;
    }

    public Object getSnow1h() {
        return snow1h;
    }

    public void setSnow1h(Object snow1h) {
        this.snow1h = snow1h;
    }
}
