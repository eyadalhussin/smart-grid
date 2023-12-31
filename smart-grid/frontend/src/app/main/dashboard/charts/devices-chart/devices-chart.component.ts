import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { Chart, ChartConfiguration } from 'chart.js/auto';
import { forkJoin } from 'rxjs';
import { colorService } from 'src/app/main/services/colorService';

interface ApiResponse{
  length:number;
}

@Component({
  selector: 'app-devices-chart',
  templateUrl: './devices-chart.component.html',
  styleUrls: ['./devices-chart.component.css']
})

export class DevicesChartComponent {
  @ViewChild('chartCanvas') chartCanvas: ElementRef | undefined;
  chart: Chart<'doughnut'> | undefined;
  devices:number[] = [0,0,0,0,0];

  constructor(private colorService:colorService, private http:HttpClient){}

  ngOnInit(): void {
    this.getDevicesCount();

    this.colorService.darkmode.subscribe(mode => this.updateColorMode(mode));
  }

  getDevicesCount(){
    console.log("getting all the devices");
    
    const url1 = "https://icecreamparty.de/api/smartgrid/conventional-power-plant";
    const url2 = "https://icecreamparty.de/api/smartgrid/wind-park";
    const url3 = "https://icecreamparty.de/api/smartgrid/solar-park";
    const url4 = "https://icecreamparty.de/api/smartgrid/energy-storage";
    const url5 = "https://icecreamparty.de/api/smartgrid/smart-home";

    const request1 = this.http.get<ApiResponse>(url1);
    const request2 = this.http.get<ApiResponse>(url2);
    const request3 = this.http.get<ApiResponse>(url3);
    const request4 = this.http.get<ApiResponse>(url4);
    const request5 = this.http.get<ApiResponse>(url5);

    forkJoin([request1, request2, request3, request4, request5]).subscribe(
      results => {
        this.devices[0] = results[0].length;
        this.devices[1] = results[1].length;
        this.devices[2] = results[2].length;
        this.devices[3] = results[3].length;
        this.devices[4] = results[4].length;
        this.initCanvas();
      }
    )
  }


  initCanvas() {
    setTimeout(() => {
      if (this.chartCanvas) {
        const ctx = this.chartCanvas.nativeElement.getContext('2d');
        this.chart = new Chart(ctx, {
          type: 'doughnut',
          data: {
            labels: ['Powerplants', 'Windparks', 'Solarparks', 'SmartHomes', 'Energy Storage'],
            datasets: [{
              data: [this.devices[0], this.devices[1], this.devices[2], this.devices[4], this.devices[3]],
              backgroundColor: ['#f44336', '#E4A11B', '#3B71CA', '#14A44D', '#5bc0de'],
              borderColor: 'white',
              borderWidth: 2,
              // hoverBackgroundColor: 'red'
            }]
          },
          options: {
            plugins: {
              legend: {
                labels: {
                  font: {
                    size: 16,
                  },
                  color: ['white']
                }
              }
            }
          }
        } as ChartConfiguration<'doughnut'>);
      }
    }, 0);
  }

  updateColorMode(value:boolean){
    if (this.chart) {
      const labels = this.chart.options.plugins?.legend?.labels;
      if (labels) {
        value == true ? labels.color = 'white' : labels.color = 'black';
      }
      this.chart.update();
    }
  }

}
