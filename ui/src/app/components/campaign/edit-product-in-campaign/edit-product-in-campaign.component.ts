import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '@app/services/product.service';
import {CampaignService} from '@app/services/campaign.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-edit-product-in-campaign',
  templateUrl: './edit-product-in-campaign.component.html',
  styleUrls: ['./edit-product-in-campaign.component.css']
})
export class EditProductInCampaignComponent implements OnInit {
  title: 'Edit Product';
  productFormGroup: FormGroup;
  categoryFormGroup: FormGroup;
  private campaign;
  private product;
  private price;
  private item;

  validMessage: string = '';

  constructor(private router: Router,
              private productService: ProductService,
              private route: ActivatedRoute,
              private campaignService: CampaignService) {
  }

  ngOnInit() {
    if (this.lsTestAuthentication()) {
      this.getProductByID(this.route.snapshot.params.id);
      this.productFormGroup = new FormGroup({
        price: new FormControl('', Validators.required)
      });
      this.categoryFormGroup = new FormGroup({
        campaign: new FormControl('', Validators.required)
      });
    } else {
      this.router.navigate(['']);
    }
  }

  getProductByID(id) {
    this.productService.getProduct(id).subscribe(
      data => {
        this.product = data;
      },
      error => console.error(error),
      () => console.log('Account Loaded')
    );
  }

  lsTestAuthentication() {
    let test = 'accountType';
    try {
      if (sessionStorage.getItem(test) === 'employee') {
        console.log('true empoyee');
        return true;
      }
      if (sessionStorage.getItem(test) === 'admin') {
        return true;
      }
      if (sessionStorage.getItem(test) === null) {
        return false;
      }
      return false;
    } catch (e) {
      return false;
    }
  }

  submitRegistration() {
    this.intializeMembers();
    if (this.validation()) {
      this.validMessage = 'Your campaign registration has been submitted. Thank you!';

      console.log('cur campaign: ' + this.campaign);

      this.campaignService.addProductToCampaign(
        this.product.productId, this.campaign, this.price).subscribe(
        data => {
          this.item = data;
          this.productFormGroup.reset();
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      );
      this.router.navigate(['product-updated']);
    }
  }

  intializeMembers() {
    this.price = this.productFormGroup.get('price').value;
  }

  validation() {
    if (!this.price.match('\\d*\\.?\\d+') || this.price.length === 0) {
      this.validMessage = 'Not Valid price';
      return false;
    }
    console.log('Validated');
    return true;
  }
}
