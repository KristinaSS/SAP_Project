import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductListKeywordComponent } from './product-list-keyword.component';

describe('ProductListKeywordComponent', () => {
  let component: ProductListKeywordComponent;
  let fixture: ComponentFixture<ProductListKeywordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductListKeywordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductListKeywordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
