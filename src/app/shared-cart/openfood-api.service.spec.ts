import { TestBed } from '@angular/core/testing';

import { OpenfoodApiService } from './openfood-api.service';

describe('OpenfoodApiService', () => {
  let service: OpenfoodApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OpenfoodApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
