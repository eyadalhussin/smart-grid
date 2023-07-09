import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tarif',
  templateUrl: './tarif.component.html',
  styleUrls: ['./tarif.component.css']
})
export class TarifComponent implements OnInit {

  tarif:number;

  constructor(private http: HttpClient){}

  ngOnInit(): void {
    this.getTarif();
    setInterval(() => {
      this.getTarif();
    }, 5000)
  }

  getTarif(){
    this.http.get<number>("https://icecreamparty.de/api/smartgrid/tarif").subscribe(erg => {
    this.tarif = erg;
    })
  }


}
