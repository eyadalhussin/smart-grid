import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnergieStorageDevicesComponent } from './energie-storage-devices.component';

describe('EnergieStorageDevicesComponent', () => {
  let component: EnergieStorageDevicesComponent;
  let fixture: ComponentFixture<EnergieStorageDevicesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EnergieStorageDevicesComponent]
    });
    fixture = TestBed.createComponent(EnergieStorageDevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
