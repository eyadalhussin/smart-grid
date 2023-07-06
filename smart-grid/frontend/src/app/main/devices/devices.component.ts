import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Powerplant } from 'src/app/classes/powerplant';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DevicesService } from 'src/app/services/devices.service';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})
export class DevicesComponent implements OnInit {

  constructor() {

  }

  ngOnInit(): void {
  }

}
