import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSightComponent } from './create-sight.component';

describe('CreateSightComponent', () => {
  let component: CreateSightComponent;
  let fixture: ComponentFixture<CreateSightComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateSightComponent]
    });
    fixture = TestBed.createComponent(CreateSightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
