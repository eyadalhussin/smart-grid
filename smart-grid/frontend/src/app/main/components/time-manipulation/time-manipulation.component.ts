import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-time-manipulation',
  templateUrl: './time-manipulation.component.html',
  styleUrls: ['./time-manipulation.component.css']
})
export class TimeManipulationComponent implements OnInit {

  accFactor: number;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getAccFactor();
  }

  getAccFactor() {
    this.http.get("https://icecreamparty.de/api/time").subscribe(erg => {
      this.accFactor = erg['accelerationFactor'];
    });
  }

  updateFactor(input: HTMLInputElement) {
    const url = `https://icecreamparty.de/api/time/acceleration?accelerationFactor=${input.value}`;
    
    this.http.post(url, null).subscribe(erg => {
      console.log("Acceleration Factor Updated");
    })
    this.accFactor = +input.value;
    input.value = "";
  }
}
