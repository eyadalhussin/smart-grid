import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  year:string;
  month:string;
  day:string;
  
  hours:string;
  minutes:string;
  seconds:string;


  constructor() {}

  ngOnInit(){
    setInterval(() => {
      this.initTime();
    }, 1000)
  }

  initTime() {
    const today = new Date();
    this.year = today.getFullYear()+"";

    let month = today.getMonth();
    let day = today.getDay();
    month < 10 ? this.month = "0" + month : this.month = month + "";
    day < 10 ? this.day = "0" + day : this.day = day + "";



    let minute = today.getMinutes();
    let second = today.getSeconds();
    let hour = today.getHours();

    minute < 10 ? this.minutes = "0" + minute : this.minutes = minute + "";
    second < 10 ? this.seconds = "0" + second : this.seconds = second + "";
    hour < 10 ? this.hours = "0" + hour : this.hours = hour + "";
  
  }

}
