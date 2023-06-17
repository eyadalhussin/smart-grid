import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { AppComponent } from './app.component';
import { MainComponent } from './main/main.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { WeatherComponent } from './main/components/weather/weather.component';
import { DashboardComponent } from './main/dashboard/dashboard.component';
import { TopBarComponent } from './main/top-bar/top-bar.component';
import { DevicesComponent } from './main/dashboard/charts/devices/devices.component';
import { HttpClientModule } from '@angular/common/http';
import { MqttModule, IMqttServiceOptions } from "ngx-mqtt";
import { TimeManipulationComponent } from './main/components/time-manipulation/time-manipulation.component';

export const MQTT_SERVICE_OPTIONS: IMqttServiceOptions = {
  hostname: '159.89.104.105',
  port: 8083,
  path: '/mqtt',
  username: 'fhdo',
  password: 'fhdo'
}

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    SidebarComponent,
    WeatherComponent,
    DashboardComponent,
    TopBarComponent,
    DevicesComponent,
    TimeManipulationComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MqttModule.forRoot(MQTT_SERVICE_OPTIONS)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
