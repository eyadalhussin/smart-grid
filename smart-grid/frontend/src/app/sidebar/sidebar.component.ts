import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
  animations: [
    trigger('sidebarAnimation', [
      state('closed', style({transform : 'scaleX(0)', transformOrigin:'left'})),
      state('open', style({transform : 'scaleX(1)', transformOrigin:'left'})),
      transition('*<=>*' , animate('300ms ease'))
    ]),
  ]
})

export class SidebarComponent implements OnInit {
  sidebarState:string = "open";

  @HostListener('window:resize', ['$event'])
  onWindowResize(event: Event) {
    
    if(window.innerWidth >= 992) this.sidebarState = "open";
  }

  constructor(private router:Router){}
  
  ngOnInit() {

  }


  openSideBar(){
    this.sidebarState = "open";
  }

  closeSideBar(){
    this.sidebarState = "closed";
  }


  onNavigate(route:string){
    this.router.navigate([route]);
  }

}
