import { Component, OnInit } from '@angular/core';
import {ProductService} from '@app/services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-product-list-out-of-stock',
  templateUrl: './product-list-out-of-stock.component.html',
  styleUrls: ['./product-list-out-of-stock.component.css']
})
export class ProductListOutOfStockComponent implements OnInit {

  public productList;

  /*todo title*/
  private validMessage: string;

  constructor(private productService: ProductService,
              private router: Router) {
  }

  ngOnInit() {
    this.getProducts();
  }

  getProducts() {
    this.productService.getOutOfStockProductList().subscribe(
      data => {
        this.productList = data;
      },
      error => {
        console.log('error thrown');
        this.validMessage = 'No products out of stock';
        return throwError(error.message || error);
      }
    );
  }

  seeProduct(product) {
    let url: string;
    if (this.lsTestAuthentication()) {
      url = '/product-em/' + product.productId;
      this.router.navigate([url]);
    } else {
      url = '/product/' + product.productId;
      console.log('url: ' + url);
      this.router.navigate([url]);
    }
  }

  lsTestAuthentication() {
    let test = 'accountType';
    try {
      if (sessionStorage.getItem(test) === 'employee') {
        return true;
      }
      if (sessionStorage.getItem(test) === 'admin') {
        return true;
      }
      if (sessionStorage.getItem(test) === null) {
        return false;
      }
      console.log('true' + sessionStorage.getItem('accountType'));
      return false;
    } catch (e) {
      return false;
    }
  }

}
