import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { WindPark } from 'src/app/classes/windPark';
import { DevicesService } from 'src/app/services/devices.service';

@Component({
  selector: 'app-windpark-devices',
  templateUrl: './windpark-devices.component.html',
  styleUrls: ['./windpark-devices.component.css']
})
export class WindparkDevicesComponent implements OnInit {

  //Forms
  windParkForm: FormGroup;
  windParks: WindPark[] = [];
  addingWindPark: boolean = false;

  constructor(private http: HttpClient, private formBuilder: FormBuilder) {

    this.windParkForm = this.formBuilder.group({
      name: ['', Validators.required],
      numberOfTurbines: ['', Validators.required],
      turbineEfficiency: ['', Validators.required],
      turbineDiameter: ['', Validators.required],
    })
  }

  ngOnInit(): void {
    this.getWindParks();
  }

  getWindParks() {
    this.http.get<WindPark[]>('https://icecreamparty.de/api/smartgrid/wind-park').subscribe(erg => {
      console.log("printing solarParks");
      this.windParks = erg;
      console.log("Getting" + this.windParks);
    });
  }

  toggleAdd(type: string) {
    switch (type) {
      case 'windPark':
        this.addingWindPark = !this.addingWindPark;
    }
  }

  onWindParkSubmit() {
    console.log("Submitting the form");
    if (this.windParkForm.valid) {
      const formData = this.windParkForm.value;
      this.http.put('https://icecreamparty.de/api/smartgrid/wind-park', formData).subscribe(erg => {
        console.log(erg);
        this.getWindParks();
      })
      console.log(formData);
      this.windParkForm.reset();
    }
  }

  onDeleteWindPark(id:number){
    console.log('Deleting ' + id);

    this.http.delete('https://icecreamparty.de/api/smartgrid/wind-park/'+id).subscribe(erg => {
    this.getWindParks();
    });
  }

}
