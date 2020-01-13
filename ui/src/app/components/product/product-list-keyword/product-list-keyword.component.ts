import { Component, OnInit } from '@angular/core';
import {ProductService} from '@app/services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-product-list-keyword',
  templateUrl: './product-list-keyword.component.html',
  styleUrls: ['./product-list-keyword.component.css']
})
export class ProductListKeywordComponent implements OnInit {
  public productList;
  public keyword;

  /*todo title*/
  private validMessage: string;

  constructor(private productService: ProductService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getProducts(this.route.snapshot.params.keyword);
    this.keyword = this.route.snapshot.params.keyword;
  }

  getProducts(keyword) {
    this.productService.getFilteredProductListByKeyword(keyword).subscribe(
      data => {
        this.productList = data;
      },
      error => {
        console.log('error thrown');
        this.validMessage = 'No products found with this keyword.';
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
