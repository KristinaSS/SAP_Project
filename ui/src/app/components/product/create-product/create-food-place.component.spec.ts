import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFoodPlaceComponent } from './create-food-place.component';

describe('CreateFoodPlaceComponent', () => {
  let component: CreateFoodPlaceComponent;
  let fixture: ComponentFixture<CreateFoodPlaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateFoodPlaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateFoodPlaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
