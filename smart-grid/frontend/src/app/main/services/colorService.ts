import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class colorService{
    darkmode = new Subject<boolean>();
    
    constructor(){}

    toggleDarkMode(value:boolean){
        this.darkmode.next(value);
    }

}