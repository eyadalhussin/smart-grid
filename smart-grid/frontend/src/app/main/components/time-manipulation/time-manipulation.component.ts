import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-time-manipulation',
  templateUrl: './time-manipulation.component.html',
  styleUrls: ['./time-manipulation.component.css']
})
export class TimeManipulationComponent implements OnInit {

  constructor(private http : HttpClient) {}

  ngOnInit(): void {
  }

  updateFactor(input:HTMLInputElement){
    const url = `https://icecreamparty.de/api/time/acceleration?accelerationFactor=${input.value}`;

    this.http.post(url, null).subscribe(
      response => {

        console.log('POST request successful!', response);
      },
      error => {

        console.error('POST request error:', error);
      }
    );
  }

}
