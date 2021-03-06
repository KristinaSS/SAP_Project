import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '@app/services/product.service';
import {Observable, throwError} from 'rxjs';
import {CampaignService} from '@app/services/campaign.service';

@Component({
  selector: 'app-add-product-to-campaign',
  templateUrl: './add-product-to-campaign.component.html',
  styleUrls: ['./add-product-to-campaign.component.css']
})
export class AddProductToCampaignComponent implements OnInit {
  title: 'Edit Product';
  productFormGroup: FormGroup;
  categoryFormGroup: FormGroup;
  private campaign;
  private product;
  private price;

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
    this.productService.getProduct(id, 'campaign').subscribe(
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
      this.campaignService.addProductToCampaign(
        this.product.productId, this.campaign, this.price).subscribe(
        data => {
          this.productFormGroup.reset();
          this.router.navigate(['product-updated']);
          return true;
        },
        error => {
          this.validMessage = 'Тhe price calculated is below the minimal';
          return throwError(error.message || error);
        }
      );
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
    if (!this.price.match('^[1-9][0-9]?$')) {
      this.validMessage = 'Not valid discount percent, needs to be from 1 - 99';
      return false;
    }
    console.log('Validated');
    return true;
  }
}
