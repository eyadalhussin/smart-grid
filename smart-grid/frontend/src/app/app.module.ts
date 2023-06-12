import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainComponent } from './main/main.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { WeatherComponent } from './main/components/weather/weather.component';
import { DashboardComponent } from './main/dashboard/dashboard.component';
import { TopBarComponent } from './main/top-bar/top-bar.component';
import { DevicesComponent } from './main/dashboard/charts/devices/devices.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    SidebarComponent,
    WeatherComponent,
    DashboardComponent,
    TopBarComponent,
    DevicesComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
