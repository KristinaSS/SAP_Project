import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ProductService} from '@app/services/product.service';
import {Observable} from 'rxjs';

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
  private description;

  validMessage: string = 'creating product: ';
  categories: Category[] = [
    {id: '1', name: 'IT'},
    {id: '2', name: 'Photography'},
    {id: '3', name: 'Electrical Appliances'},
    {id: '4', name: 'Clothes'},
    {id: '5', name: 'Health and Care'},
    {id: '6', name: 'Books'},
    {id: '7', name: 'Toys'},
    {id: '8', name: 'Sport'},
    {id: '9', name: 'Food'},
    {id: '10', name: 'Other'}
  ];

  constructor(private router: Router,
              private  productService: ProductService) {
  }

  ngOnInit() {
    this.productFormGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      quantity: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });
    this.categoryFormGroup = new FormGroup({
      category: new FormControl('', Validators.required)
    });
  }

  submitRegistration() {
    this.intializeMembers();
    if (this.validation()) {
      this.validMessage = 'Your food place registration has been submitted. Thank you!';
      this.productService.createProduct(this.name, this.quantity, this.price, this.description, this.categoryId.name).subscribe(
        data => {
          this.productFormGroup.reset();
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      );
      this.router.navigate(['']);
    } else {
      this.validMessage = 'Please fill out this form before submitting';
    }
  }

  intializeMembers() {
    this.name = this.productFormGroup.get('name').value;
    this.quantity = this.productFormGroup.get('quantity').value;
    this.price = this.productFormGroup.get('price').value;
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

export interface Category {
  id: string;
  name: string;
}
