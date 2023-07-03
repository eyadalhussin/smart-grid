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
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { MqttModule, IMqttServiceOptions } from "ngx-mqtt";
import { TimeManipulationComponent } from './main/components/time-manipulation/time-manipulation.component';
import { AppRoutingModule } from './app-routing.module';
import { SpinnerComponent } from './main/components/spinner/spinner.component';
import { DevicesComponent } from './main/devices/devices.component';
import { DevicesChartComponent } from './main/dashboard/charts/devices-chart/devices-chart.component';

export const MQTT_SERVICE_OPTIONS: IMqttServiceOptions = {
  hostname: '159.89.104.105',
  port: 8083,
  path: '/mqtt',
  username: 'fhdo',
  password: 'fhdo'
}

// CORS configuration
export function configureCORSHeaders(http: HttpClient) {
  return () => {
    const headers = new HttpHeaders();
    headers.set('Access-Control-Allow-Origin', '*');
    headers.set('Access-Control-Allow-Methods', '*');
    headers.set('Access-Control-Allow-Headers', '*');
    headers.set('Access-Control-Allow-Credentials', 'true');
    // Use the headers with your requests
    // Example:
    // http.get('your-url', { headers }).subscribe(...);
  };
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
  providers: [{
    provide: APP_INITIALIZER,
    useFactory: configureCORSHeaders,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
