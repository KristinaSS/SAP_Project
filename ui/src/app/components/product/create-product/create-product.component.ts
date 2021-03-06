import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ProductService} from '@app/services/product.service';
import {Observable, throwError} from 'rxjs';

@Component({
  selector: 'app-edit-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

  title: 'Edit Product';
  productFormGroup: FormGroup;
  categoryFormGroup: FormGroup;
  private categoryId;
  private name;
  private quantity;
  private price;
  private minPrice;
  private description;

  validMessage: string = 'creating product: ';

  constructor(private router: Router,
              private  productService: ProductService) {
  }

  ngOnInit() {
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
  }

  submitRegistration() {
    this.intializeMembers();
    if (this.validation()) {
      console.log(this.categoryId.name);
      this.validMessage = 'Your food place registration has been submitted. Thank you!';
      this.productService.createProduct(this.name, this.quantity, this.price, this.minPrice, this.description, this.categoryId).subscribe(
        data => {
          this.productFormGroup.reset();
          return true;
        },
        error => {
          console.log('error thrown');
          this.validMessage = 'Min Price cannot be less more than regular price, and cannot be 0 or less. ' +
            'Regular price cannot be 0 or less';
          return throwError(error.message || error);
        }
      );
      this.router.navigate(['product-created']);
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
      this.validMessage = 'Not valid name';
      return false;
    }
    if (this.price.length === 0 || !this.price.match('\\d*\\.?\\d+')) {
      this.validMessage = 'Not Valid price';
      return false;
    }
    if (this.minPrice.length === 0 || !this.minPrice.match('\\d*\\.?\\d+')) {
      this.validMessage = 'Not Valid price';
      return false;
    }
    if (this.description.length === 0) {
      this.validMessage = 'Please fill out description';
      return false;
    }
    if (this.quantity.length === 0 || !this.quantity.match('^(0|[1-9][0-9]{0,9})$')) {
      this.validMessage = 'Not valid quantity';
      return false;
    }
    console.log('Validated');
    return true;
  }
}
