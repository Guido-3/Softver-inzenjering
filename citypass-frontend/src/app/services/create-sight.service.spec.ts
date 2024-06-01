import { TestBed } from '@angular/core/testing';

import { CreateSightService } from './create-sight.service';

describe('CreateSightService', () => {
  let service: CreateSightService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateSightService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
