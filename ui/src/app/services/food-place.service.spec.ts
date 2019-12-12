import { TestBed } from '@angular/core/testing';

import { FoodPlaceService } from './food-place.service';

describe('FoodPlaceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FoodPlaceService = TestBed.get(FoodPlaceService);
    expect(service).toBeTruthy();
  });
});
