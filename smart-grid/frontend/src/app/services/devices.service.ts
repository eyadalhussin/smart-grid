import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

@Injectable({providedIn:'root'})
export class DevicesService{
    isLoading:boolean = true;
    isLoadingSubject = new Subject<boolean>();

    //0: Powerplant, 1: Solarpark, 2: Windpark, 3: Smarthomes
    devicesCount:number[] = [0,0,0,0];
    devicesLoading:boolean[] = [true,true,true,false];

    checkLoadingStatus(index:number, count:number){
        this.devicesCount[index] = count;
        this.devicesLoading[index] = false;
        this.checkAllDevices();
    }

    checkAllDevices(){
        //if it includes true, the variable is also true, which means we are still loading
        this.isLoading = this.devicesLoading.includes(true);
        if(this.isLoading === false){
            this.isLoadingSubject.next(false);
        } 
    }

}