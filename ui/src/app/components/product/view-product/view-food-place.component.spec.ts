import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFoodPlaceComponent } from './view-food-place.component';

describe('ViewFoodPlaceComponent', () => {
  let component: ViewFoodPlaceComponent;
  let fixture: ComponentFixture<ViewFoodPlaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewFoodPlaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFoodPlaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
