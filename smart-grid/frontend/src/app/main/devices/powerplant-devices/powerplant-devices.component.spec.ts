import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PowerplantDevicesComponent } from './powerplant-devices.component';

describe('PowerplantDevicesComponent', () => {
  let component: PowerplantDevicesComponent;
  let fixture: ComponentFixture<PowerplantDevicesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PowerplantDevicesComponent]
    });
    fixture = TestBed.createComponent(PowerplantDevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
