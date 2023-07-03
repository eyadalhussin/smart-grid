import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Powerplant } from 'src/app/classes/powerplant';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})
export class DevicesComponent implements OnInit {

  addingSolarpark: boolean = false;
  addingWindpark: boolean = false;

  solarParks = [];
  windParks = [];
  smartHomes = [];

  lol: string = "lol";

  constructor(private http: HttpClient) {

  }

  ngOnInit(): void {
    //this.getKraftwerks();
  }

  getKraftwerks() {

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
