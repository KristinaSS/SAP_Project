import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiningPlacesListComponent } from './dining-places-list.component';

describe('AdminComponent', () => {
  let component: DiningPlacesListComponent;
  let fixture: ComponentFixture<DiningPlacesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiningPlacesListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiningPlacesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
