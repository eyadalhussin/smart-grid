import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './main/dashboard/dashboard.component';
import { DevicesComponent } from './main/devices/devices.component';
import { ScheduleComponent } from './main/schedule/schedule.component';
import { HistoryComponent } from './main/history/history.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'devices', component: DevicesComponent},
  { path: 'schedules', component: ScheduleComponent},
  { path: 'history', component: HistoryComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }