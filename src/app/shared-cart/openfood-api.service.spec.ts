import { TestBed } from '@angular/core/testing';

import { OpenfoodApiService } from './openfood-api.service';

describe('OpenfoodApiService', () => {
  let service: OpenFoodApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OpenFoodApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
