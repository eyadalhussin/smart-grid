import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SmartHome } from 'src/app/classes/smartHome';

@Component({
  selector: 'app-smarthome-devices',
  templateUrl: './smarthome-devices.component.html',
  styleUrls: ['./smarthome-devices.component.css']
})
export class SmarthomeDevicesComponent {
  formValidState:string = 'empty';
  //Forms
  smartHomeForm: FormGroup;
  smartHomes: SmartHome[] = [];
  addingSmartHome: boolean = false;

  constructor(private http: HttpClient, private formBuilder: FormBuilder) {
    this.smartHomeForm = this.formBuilder.group({
      url: ['', Validators.required],
      currentPowerConsumption: ['', Validators.required],
    })
  }

  ngOnInit(): void {
    this.getSmartHomes();
  }

  getSmartHomes() {
    this.http.get<SmartHome[]>('https://icecreamparty.de/api/smartgrid/smart-home').subscribe(erg => {
    this.smartHomes = erg;
    });
  }

  toggleAdd(type: string) {
    switch (type) {
      case 'smartHome':
        this.addingSmartHome = !this.addingSmartHome;
    }
  }

  onSmartHomeSubmit() {
    console.log("Submitting the form");
    if (this.smartHomeForm.valid) {
      const formData = this.smartHomeForm.value;
      console.log(formData);
      this.http.put('https://icecreamparty.de/api/smartgrid/smart-home', formData).subscribe(erg => {
        console.log(erg);
        this.getSmartHomes();
      });
      this.formValidState = 'empty';
      this.smartHomeForm.reset();
    }
    else {
      this.formValidState = 'invalid';
    }
  }

  onSmartHomeDelete(id:number){
    this.http.delete('https://icecreamparty.de/api/smartgrid/smart-home/'+id).subscribe(erg => {
      this.getSmartHomes();
    });
  }
}
