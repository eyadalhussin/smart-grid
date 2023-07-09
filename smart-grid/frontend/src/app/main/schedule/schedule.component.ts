import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Schedule } from 'src/app/classes/schedule';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

  schedules: Schedule[] = [];


  constructor(private http: HttpClient, private datePipe: DatePipe) {

  }

  ngOnInit(): void {
    this.getSchedules();
  }

  getSchedules() {
    this.http.get<Schedule[]>('https://icecreamparty.de/api/smartgrid/schedule').subscribe(erg => {
      this.schedules = erg;
      console.log(this.schedules);

    });
  }


  convertUnixTimeStamp(timestamp: number) {
    const date = new Date(timestamp * 1000);
    const formattedDate = this.datePipe.transform(date, 'yyyy-MM-dd HH:mm:ss');
    return formattedDate;
  }

  getFormattedDate(timestamp: number): string {
    const date = new Date((timestamp - 2 * 60 * 60) * 1000); // Subtract 2 hours (2 * 60 * 60 seconds)
    const formattedDate = this.datePipe.transform(date, 'dd.MM.yyyy HH:mm');
    return formattedDate || '';
  }

  getFormattedTime(timestamp: number): string {
    const hours = Math.floor(timestamp / 3600);
    const minutes = Math.floor((timestamp % 3600) / 60);

    const hoursString = hours > 0 ? `${hours} hour${hours !== 1 ? 's' : ''}` : '';
    const minutesString = minutes > 0 ? `${minutes} minute${minutes !== 1 ? 's' : ''}` : '';

    return `${hoursString} ${minutesString}`.trim();
  }

}
