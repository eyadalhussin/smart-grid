import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolarparkDevicesComponent } from './solarpark-devices.component';

describe('SolarparkDevicesComponent', () => {
  let component: SolarparkDevicesComponent;
  let fixture: ComponentFixture<SolarparkDevicesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SolarparkDevicesComponent]
    });
    fixture = TestBed.createComponent(SolarparkDevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
