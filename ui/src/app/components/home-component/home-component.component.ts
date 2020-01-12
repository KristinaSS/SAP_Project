import { Component, OnInit } from '@angular/core';
import {ProductService} from '@app/services/product.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {

  public productList;

  constructor(private productService: ProductService,
              private router: Router) {
  }

  ngOnInit() {
    this.getProducts();
  }

  getProducts() {
    this.productService.getAllAvailableProducts().subscribe(
      data => {
        this.productList = data;
      },
      error => console.error(error),
      () => console.log('Products Loaded')
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
        return false;      }
      console.log('true' + sessionStorage.getItem('accountType'));
      return false;
    } catch (e) {
      return false;
    }
  }
}
