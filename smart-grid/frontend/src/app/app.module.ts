import { NgModule, APP_INITIALIZER } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { MainComponent } from './main/main.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { WeatherComponent } from './main/components/weather/weather.component';
import { DashboardComponent } from './main/dashboard/dashboard.component';
import { TopBarComponent } from './main/top-bar/top-bar.component';
import { HttpClientModule } from '@angular/common/http';
import { MqttModule, IMqttServiceOptions } from "ngx-mqtt";
import { TimeManipulationComponent } from './main/components/time-manipulation/time-manipulation.component';
import { AppRoutingModule } from './app-routing.module';
import { SpinnerComponent } from './main/components/spinner/spinner.component';
import { DevicesComponent } from './main/devices/devices.component';
import { DevicesChartComponent } from './main/dashboard/charts/devices-chart/devices-chart.component';
import { PowerplantDevicesComponent } from './main/devices/powerplant-devices/powerplant-devices.component';
import { SolarparkDevicesComponent } from './main/devices/solarpark-devices/solarpark-devices.component';
import { WindparkDevicesComponent } from './main/devices/windpark-devices/windpark-devices.component';
import { EnergieStorageDevicesComponent } from './main/devices/energie-storage-devices/energie-storage-devices.component';
import { EnergyInfoComponent } from './main/dashboard/energy-info/energy-info.component';
import { TarifComponent } from './main/dashboard/tarif/tarif.component';
import { SmarthomeDevicesComponent } from './main/devices/smarthome-devices/smarthome-devices.component';
import { ScheduleComponent } from './main/schedule/schedule.component';
import { DatePipe } from '@angular/common';

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
    TimeManipulationComponent,
    SpinnerComponent,
    DevicesComponent,
    DevicesChartComponent,
    PowerplantDevicesComponent,
    SolarparkDevicesComponent,
    WindparkDevicesComponent,
    EnergieStorageDevicesComponent,
    EnergyInfoComponent,
    TarifComponent,
    SmarthomeDevicesComponent,
    ScheduleComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MqttModule.forRoot(MQTT_SERVICE_OPTIONS),
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
