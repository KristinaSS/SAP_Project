import { TestBed } from '@angular/core/testing';

import { AccountTypeServiceService } from './account-type-service.service';

describe('AccountTypeServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AccountTypeServiceService = TestBed.get(AccountTypeServiceService);
    expect(service).toBeTruthy();
  });
});
