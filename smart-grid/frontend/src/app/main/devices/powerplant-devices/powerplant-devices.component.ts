import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Powerplant } from 'src/app/classes/powerplant';
import { DevicesService } from 'src/app/services/devices.service';

@Component({
  selector: 'app-powerplant-devices',
  templateUrl: './powerplant-devices.component.html',
  styleUrls: ['./powerplant-devices.component.css']
})
export class PowerplantDevicesComponent implements OnInit {

  //Forms
  powerplantForm: FormGroup;
  powerplants: Powerplant[] = [];
  addingPowerplant: boolean = false;

  constructor(private http: HttpClient, private formBuilder: FormBuilder){
    this.powerplantForm = this.formBuilder.group({
      name: ['', Validators.required],
      generators: ['', Validators.required],
      powerGeneration: ['', Validators.required],
      fuelType: ['', Validators.required]
    })
  }

  ngOnInit(): void {
    this.getPowerPlants();
  }

  getPowerPlants(){
    this.http.get<Powerplant[]>('https://icecreamparty.de/api/smartgrid/conventional-power-plant').subscribe(erg => {
      console.log("printing powerplants");
      this.powerplants = erg;
      console.log("Getting" + this.powerplants);
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

    if (this.powerplantForm.valid) {
      const formData = this.powerplantForm.value;
      this.http.put('/api/smartgrid/conventional-power-plant', formData).subscribe(erg => {
        console.log(erg);
      });
      console.log(formData); // You can access the form values here
      this.powerplantForm.reset();
    }
  }
}
