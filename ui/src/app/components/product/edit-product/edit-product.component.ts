import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '@app/services/product.service';
import {CartService} from '@app/services/cart.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  title: 'Edit Product';
  productFormGroup: FormGroup;
  categoryFormGroup: FormGroup;
  private categoryId;
  private product;
  private name;
  private quantity;
  private price;
  private minPrice;
  private description;

  validMessage: string = '';

  constructor(private router: Router,
              private productService: ProductService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    if (this.lsTestAuthentication()) {
      console.log('id from route: ' + this.route.snapshot.params.id);
      this.getProductByID(this.route.snapshot.params.id);
      this.productFormGroup = new FormGroup({
        name: new FormControl('', Validators.required),
        quantity: new FormControl('', Validators.required),
        price: new FormControl('', Validators.required),
        minPrice: new FormControl('', Validators.required),
        description: new FormControl('', Validators.required)
      });
      this.categoryFormGroup = new FormGroup({
        category: new FormControl('', Validators.required)
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
      this.productService.editProduct(
        this.product.productId,
        this.name,
        this.quantity,
        this.price,
        this.minPrice,
        this.description,
        this.categoryId).subscribe(
        data => {
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
    this.name = this.productFormGroup.get('name').value;
    this.quantity = this.productFormGroup.get('quantity').value;
    this.price = this.productFormGroup.get('price').value;
    this.minPrice = this.productFormGroup.get('minPrice').value;
    this.description = this.productFormGroup.get('description').value;
  }

  validation() {
    if (this.name.length === 0) {
      this.name = this.product.name;
    }
    if (!this.price.match('\\d*\\.?\\d+')) {
      if (this.price.length === 0) {
        this.price = this.product.price;
      } else {
        this.validMessage = 'Not Valid price';
        return false;
      }
    }
    if (!this.minPrice.match('\\d*\\.?\\d+')) {
      if (this.minPrice.length === 0) {
        this.minPrice = this.product.minPrice;
      } else {
        this.validMessage = 'Not Valid min price';
        return false;
      }
    }
    if (this.description.length === 0) {
      this.description = this.product.discription;
    }
    if (this.quantity.match('^(0|[1-9][0-9]{0,9})$')) {
      if (this.quantity.length === 0) {
        this.quantity = '0';
      } else {
        this.validMessage = 'Not valid quantity';
        return false;
      }
    }
    console.log('Validated');
    return true;
  }
}
