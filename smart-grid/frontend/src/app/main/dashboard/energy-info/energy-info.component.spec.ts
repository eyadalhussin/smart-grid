import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnergyInfoComponent } from './energy-info.component';

describe('EnergyInfoComponent', () => {
  let component: EnergyInfoComponent;
  let fixture: ComponentFixture<EnergyInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EnergyInfoComponent]
    });
    fixture = TestBed.createComponent(EnergyInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
