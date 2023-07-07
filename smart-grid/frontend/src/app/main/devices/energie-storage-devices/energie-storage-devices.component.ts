import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EnergieStorage } from 'src/app/classes/energieStorage';
import { SolarPark } from 'src/app/classes/solarPark';

@Component({
  selector: 'app-energie-storage-devices',
  templateUrl: './energie-storage-devices.component.html',
  styleUrls: ['./energie-storage-devices.component.css']
})
export class EnergieStorageDevicesComponent implements OnInit{

  //Forms
  energieStorageForm: FormGroup;
  energieStorages: EnergieStorage[] = [];
  addingEnergieStorage: boolean = false;

  constructor(private http: HttpClient, private formBuilder: FormBuilder) {
    this.energieStorageForm = this.formBuilder.group({
      name: ['', Validators.required],
      capacity: ['', Validators.required],
    })
  }

  ngOnInit(): void {
    this.getEnergieStorages();
  }

  getEnergieStorages() {
    this.http.get<EnergieStorage[]>('https://icecreamparty.de/api/smartgrid/energy-storage').subscribe(erg => {
    this.energieStorages = erg;
    });
  }

  toggleAdd(type: string) {
    switch (type) {
      case 'energieStorage':
        this.addingEnergieStorage = !this.addingEnergieStorage;
    }
  }

  onEnergieStorageSubmit() {
    console.log("Submitting the form");
    if (this.energieStorageForm.valid) {
      const formData = this.energieStorageForm.value;
      console.log(formData);
      this.http.put('https://icecreamparty.de/api/smartgrid/energy-storage', formData).subscribe(erg => {
        console.log(erg);
        this.getEnergieStorages();
      });
      this.energieStorageForm.reset();
    }
  }

  onEnergieStorageDelete(id:number){
    this.http.delete('https://icecreamparty.de/api/smartgrid/energy-storage/'+id).subscribe(erg => {
      this.getEnergieStorages();
    });
  }
}
