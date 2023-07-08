import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SolarPark } from 'src/app/classes/solarPark';

@Component({
  selector: 'app-solarpark-devices',
  templateUrl: './solarpark-devices.component.html',
  styleUrls: ['./solarpark-devices.component.css']
})

export class SolarparkDevicesComponent {
  formValidState:string = 'empty';
  //Forms
  solarParkForm: FormGroup;
  solarParks: SolarPark[] = [];
  addingSolarPark: boolean = false;

  constructor(private http: HttpClient, private formBuilder: FormBuilder) {
    this.solarParkForm = this.formBuilder.group({
      name: ['', Validators.required],
      numberOfCells: ['', Validators.required],
      cellEfficiency: ['', Validators.required],
    })
  }

  ngOnInit(): void {
    this.getSolarParks();
  }

  getSolarParks() {
    this.http.get<SolarPark[]>('https://icecreamparty.de/api/smartgrid/solar-park').subscribe(erg => {
    this.solarParks = erg;
      // console.log("Getting" + this.solarParks);
    });
  }

  toggleAdd(type: string) {
    switch (type) {
      case 'solarPark':
        this.addingSolarPark = !this.addingSolarPark;
    }
  }

  onSolarParkSubmit() {
    console.log("Submitting the form");
    if (this.solarParkForm.valid) {
      const formData = this.solarParkForm.value;
      console.log(formData);
      
      this.http.put('https://icecreamparty.de/api/smartgrid/solar-park', formData).subscribe(erg => {
        this.getSolarParks();
        this.formValidState = 'empty';
        this.solarParkForm.reset();
      });
    } else {
      this.formValidState = 'invalid';
    }
  }

  onSolarParkDelete(id:number){
    this.http.delete('https://icecreamparty.de/api/smartgrid/solar-park/'+id).subscribe(erg => {
      this.getSolarParks();
    });
  }

}
