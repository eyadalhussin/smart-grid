import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmarthomeDevicesComponent } from './smarthome-devices.component';

describe('SmarthomeDevicesComponent', () => {
  let component: SmarthomeDevicesComponent;
  let fixture: ComponentFixture<SmarthomeDevicesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SmarthomeDevicesComponent]
    });
    fixture = TestBed.createComponent(SmarthomeDevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
