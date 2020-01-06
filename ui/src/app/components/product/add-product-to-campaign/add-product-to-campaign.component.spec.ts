import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProductToCampaignComponent } from './add-product-to-campaign.component';

describe('AddProductToCampaignComponent', () => {
  let component: AddProductToCampaignComponent;
  let fixture: ComponentFixture<AddProductToCampaignComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddProductToCampaignComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProductToCampaignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
