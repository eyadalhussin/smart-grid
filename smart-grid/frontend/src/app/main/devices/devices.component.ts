import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})
export class DevicesComponent implements OnInit {
  isLoading: boolean = true;
  tmp = [1,2,3,4,5,6,7,8,9,10];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getKraftwerks();

  } 

  getKraftwerks() {
    let powerPlants = this.http.get('https://icecreamparty.de/api/smartgrid/conventional-power-plant').subscribe(erg => {
      console.log("printing powerplants");
      
    console.log(erg);
    });

    let ss = this.http.get('https://icecreamparty.de/api/smartgrid/solar-park').subscribe(erg => {
      console.log("printing powerplants");
      
    console.log(erg);
    });

    let xy = this.http.get('https://icecreamparty.de/api/smartgrid/wind-park').subscribe(erg => {
      console.log("printing powerplants");
      
    console.log(erg);
    });
  }
}
