import { TestBed } from '@angular/core/testing';

import { AccountServiceService } from './account-service.service';

describe('AccountServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AccountServiceService = TestBed.get(AccountServiceService);
    expect(service).toBeTruthy();
  });
});
