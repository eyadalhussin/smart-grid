import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';

interface ApiResponse{
  value:number;
}

@Component({
  selector: 'app-energy-info',
  templateUrl: './energy-info.component.html',
  styleUrls: ['./energy-info.component.css']
})

export class EnergyInfoComponent implements OnInit, OnDestroy {

  consumption:number = 0;
  production:number = 0;
  energyPercentageStored:number = 0;
  totalEnergyStored:number = 0;

  infoInterval;

  ngOnInit(): void {
    this.getInformations();
    this.infoInterval = setInterval(()=> {
      this.getInformations();
    } , 5000);
  }

  ngOnDestroy(): void {
    clearInterval(this.infoInterval);
  }

  constructor(private http: HttpClient){}

  getInformations(){
      const url1 = "https://icecreamparty.de/api/smartgrid/energy/consumption";
      const url2 = "https://icecreamparty.de/api/smartgrid/energy/production";
      const url3 = "https://icecreamparty.de/api/smartgrid/energy/energy-percentage-stored";
      const url4 = "https://icecreamparty.de/api/smartgrid/energy/total-energy-stored";
  
      const request1 = this.http.get(url1);
      const request2 = this.http.get(url2);
      const request3 = this.http.get(url3);
      const request4 = this.http.get(url4);
  
      forkJoin([request1, request2, request3, request4]).subscribe(
        results => {
          this.consumption = Math.floor(<number>results[0]);
          this.production = Math.floor(<number>results[1]);
          this.energyPercentageStored = Math.floor(<number>results[2]);
          this.totalEnergyStored = Math.floor(<number>results[3]);
        }
      )
  }

  
}
