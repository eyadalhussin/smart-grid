import { Component, OnDestroy, OnInit } from '@angular/core';
import { IMqttMessage, MqttService } from 'ngx-mqtt';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css'],
})

export class WeatherComponent implements OnInit, OnDestroy {
  year: number;
  month: number;
  day: number;
  hours: number;
  minutes: number;
  seconds: number;

  temp: number = 19;
  humidity: number = 19;
  windSpeed: number = 19;
  cloud: number = 19;

  weatherIcon: string = "DayClear";

  constructor(private _mqttService: MqttService) { }

  private timeSubscription: Subscription;
  private weatherSubscription: Subscription;

  timeTopicName: string = 'fhdo/time';
  weatherTopicName: string = 'fhdo/weather';


  ngOnInit() {
    this.initTime();
    this.timeSubscribe();
    this.weatherSubcribe();
    //If Seconds should also be shown, the Interval should be destroyed with ngOnDestroy
    setInterval(() => {
      this.seconds++;
    }, 1000);
  }

  ngOnDestroy(): void {
    this.timeSubscription.unsubscribe();
    this.weatherSubscription.unsubscribe();
  }

  timeSubscribe(): void {
    this.timeSubscription = this._mqttService.observe(this.timeTopicName).subscribe((message: IMqttMessage) => {
      const payload = message.payload.toString();
      const jsonPayLoad = JSON.parse(payload);
      this.updateTime(jsonPayLoad);
    });
  }

  weatherSubcribe(): void {
    this.weatherSubscription = this._mqttService.observe(this.weatherTopicName).subscribe((message: IMqttMessage) => {
      const payload = message.payload.toString();
      const jsonPayLoad = JSON.parse(payload);
      this.updateWeather(jsonPayLoad);
    });
  }

  //Initialise the Time at the Start of the programm
  initTime() {
    const today = new Date();
    this.year = today.getFullYear();
    this.month = today.getMonth();
    this.day = today.getDay();
    this.hours = today.getHours();
    this.minutes = today.getMinutes();
    this.seconds = today.getSeconds();
  }

  //This method will be called each time a new message is sent via MQTT about the TIME
  updateTime(message: JSON) {
    let date = message['date'].toString();
    let time = message['time'].toString();

    this.year = date.substring(0, 4);
    this.month = date.substring(5, 7);
    this.day = date.substring(8, 10);
    this.hours = time.substring(0, 2);
    this.minutes = time.substring(3, 5);
    this.seconds = 0;
  }

  //This method will be called each time a new message is sent via MQTT about the WEATHER
  updateWeather(message: JSON) {
    console.log(message);
    this.updateWeatherIcon(message['weatherName']);
    this.temp = message['temp'];
    this.humidity = message['humidity'];
    this.windSpeed = message['windSpeed'];
    this.cloud = message['cloud'];
  }

  updateWeatherIcon(name: string) {
    let day;
    this.hours > 18 ? day = "Night" : day = "Day";
    switch (name) {
      case 'Clear':
        this.weatherIcon = day + "Clear";
        break;
      case 'Mist':
        this.weatherIcon = "Mist";
        break;
      case 'Clouds':
        this.weatherIcon = day + "Clouds";
        break;
      case 'Fog':
        this.weatherIcon = "Fog";
        break;
      case 'Rain':
        this.weatherIcon = "Rain";
        break;
      case 'Drizzle':
        this.weatherIcon = "Drizzle";
        break;
      case 'Thunderstorm':
        this.weatherIcon = "Thunderstorm";
        break;
      case 'Snow':
        this.weatherIcon = "Snow";
        break;
    }
  }
}
