import { Component } from '@angular/core';
import { colorService } from 'src/app/main/services/colorService';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent {
  darkmode:boolean = false;

  constructor(private colorService: colorService){}
  
  toggleDarkMode(){
    if(this.darkmode){
      document.documentElement.style.setProperty('--backgroundPrim', '#F0F4F7');
      document.documentElement.style.setProperty('--backgroundSec', '#ffffff');
      document.documentElement.style.setProperty('--backgroundGrey', '#EBEDEF');
      document.documentElement.style.setProperty('--white', '#ffffff');
      document.documentElement.style.setProperty('--black', '#000000');
      document.documentElement.style.setProperty('--primDark', '#226CA5');
      document.documentElement.style.setProperty('--scrollbarColor', '#226CA5');
      document.documentElement.style.setProperty('--backgroundGradient', 'linear-gradient(180deg, #62B8F6 0%, #2C79C1 100%)');
      this.darkmode = false;
      this.colorService.toggleDarkMode(false);
    } else {
      document.documentElement.style.setProperty('--backgroundPrim', '#181924');
      document.documentElement.style.setProperty('--backgroundSec', '#2c3446');
      document.documentElement.style.setProperty('--backgroundGrey', '#EBEDEF');
      document.documentElement.style.setProperty('--white', '#000000');
      document.documentElement.style.setProperty('--black', '#ffffff');
      document.documentElement.style.setProperty('--primDark', '#181924');
      document.documentElement.style.setProperty('--scrollbarColor', '#B1B1B1');
      document.documentElement.style.setProperty('--backgroundGradient', 'linear-gradient(180deg, #25293B 50%, #37445B 100%)');
      this.darkmode = true;
      this.colorService.toggleDarkMode(true);
    }
  }
}
