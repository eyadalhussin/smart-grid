import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WindparkDevicesComponent } from './windpark-devices.component';

describe('WindparkDevicesComponent', () => {
  let component: WindparkDevicesComponent;
  let fixture: ComponentFixture<WindparkDevicesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WindparkDevicesComponent]
    });
    fixture = TestBed.createComponent(WindparkDevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
