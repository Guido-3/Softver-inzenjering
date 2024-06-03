import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DailyPassesComponent } from './daily-passes.component';

describe('DailyPassesComponent', () => {
  let component: DailyPassesComponent;
  let fixture: ComponentFixture<DailyPassesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DailyPassesComponent]
    });
    fixture = TestBed.createComponent(DailyPassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
