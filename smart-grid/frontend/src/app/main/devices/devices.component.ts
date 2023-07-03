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

  //Forms
  powerplantForm: FormGroup;

  isLoading: boolean = true;
  tmp = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];


  addingPowerplant: boolean = false;
  addingSolarpark: boolean = false;
  addingWindpark: boolean = false;

  powerplants: Powerplant[] = [];
  solarParks = [];
  windParks = [];
  smartHomes = [];

  lol: string = "lol";

  constructor(private http: HttpClient, private formBuilder: FormBuilder) {
    this.powerplantForm = this.formBuilder.group({
      name: ['', Validators.required],
      generators: ['', Validators.required],
      powerGeneration: ['', Validators.required],
      fuelType: ['', Validators.required]
    })

  }

  ngOnInit(): void {
    this.getKraftwerks();
  }

  getKraftwerks() {
    this.http.get<Powerplant[]>('https://icecreamparty.de/api/smartgrid/conventional-power-plant').subscribe(erg => {
      console.log("printing powerplants");
      this.powerplants = erg;
      console.log("Getting" + this.powerplants);
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


  toggleAdd(type: string) {
    switch (type) {
      case 'powerplant':
        this.addingPowerplant = !this.addingPowerplant;
    }
  }

  onPowerplantSubmit() {
    console.log("Submitting the form");
    
    if(this.powerplantForm.valid){
      const formData = this.powerplantForm.value;
      this.http.put('https://icecreamparty.de/api/smartgrid/conventional-power-plant', formData).subscribe(erg => {
        console.log(erg);
      })
      console.log(formData); // You can access the form values here
      this.powerplantForm.reset();
    }
  }


}
