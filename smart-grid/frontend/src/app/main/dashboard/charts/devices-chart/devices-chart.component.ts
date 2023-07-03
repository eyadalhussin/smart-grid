import { Component, ElementRef, ViewChild } from '@angular/core';
import { Chart, ChartConfiguration } from 'chart.js/auto';
import { colorService } from 'src/app/main/services/colorService';

@Component({
  selector: 'app-devices-chart',
  templateUrl: './devices-chart.component.html',
  styleUrls: ['./devices-chart.component.css']
})

export class DevicesChartComponent {
  @ViewChild('chartCanvas') chartCanvas: ElementRef | undefined;
  chart: Chart<'doughnut'> | undefined;

  constructor(private colorService:colorService){}

  ngOnInit(): void {
    this.initCanvas();
    this.colorService.darkmode.subscribe(mode => this.updateColorMode(mode));
  }

  initCanvas() {
    setTimeout(() => {
      if (this.chartCanvas) {
        const ctx = this.chartCanvas.nativeElement.getContext('2d');
        this.chart = new Chart(ctx, {
          type: 'doughnut',
          data: {
            labels: ['Homes', 'Solaranlage', 'Windpark', 'Kraftwerk'],
            datasets: [{
              data: [4, 6, 15, 2],
              backgroundColor: ['#f44336', '#E4A11B', '#3B71CA', '#14A44D'],
              borderColor: 'white',
              borderWidth: 2,
              hoverBackgroundColor: 'red'
            }]
          },
          options: {
            plugins: {
              legend: {
                labels: {
                  font: {
                    size: 21,
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
