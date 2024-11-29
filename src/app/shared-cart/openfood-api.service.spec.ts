import { TestBed } from '@angular/core/testing';

import { OpenFoodApiService } from './openfood-api.service';

describe('OpenFoodApiService', () => {
  let service: OpenFoodApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OpenfoodApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
