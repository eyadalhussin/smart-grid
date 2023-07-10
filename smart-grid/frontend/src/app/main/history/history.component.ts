import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { History } from 'src/app/classes/history';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  histories:History[] = [];

  constructor(private http: HttpClient, private datePipe: DatePipe){}

  ngOnInit(): void {
    this.getHistories();
  }

  getHistories(){
    this.http.get<History[]>("https://icecreamparty.de/api/smartgrid/history").subscribe(erg => {
      this.histories = erg;
    })
  }

  getValue(value: number){
    return value.toFixed(2);
  }

  getFormattedDate(timestamp: number): string {
    const date = new Date((timestamp - 2 * 60 * 60) * 1000); // Subtract 2 hours (2 * 60 * 60 seconds)
    const formattedDate = this.datePipe.transform(date, 'dd.MM.yyyy HH:mm');
    return formattedDate || '';
  }


}
