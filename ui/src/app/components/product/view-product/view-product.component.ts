import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '@app/services/product.service';
import {CartService} from '@app/services/cart.service';

@Component({
  selector: 'app-view-product',
  templateUrl: './view-product.component.html',
  styleUrls: ['./view-product.component.css']
})
export class ViewProductComponent implements OnInit {

  constructor(private router: Router,
              private productService: ProductService,
              private route: ActivatedRoute,
              private cartService: CartService) {
  }

  public product;
  public selectOption;
  public result;

  ngOnInit() {
    console.log('id from route: ' + this.route.snapshot.params.id);
    this.getProductByID(this.route.snapshot.params.id);
    /*    console.log(this.product.discription);*/
  }

  getProductByID(id) {
    this.productService.getProduct(id, 'view').subscribe(
      data => {
        this.product = data;
      },
      error => console.error(error),
      () => console.log('Account Loaded')
    );
  }

  addProductToCart() {
    let result: boolean;
    result = this.lsTest();
    console.log('result: ' + result);
    if (result) {
      console.log('selected option local storage: ' + sessionStorage.getItem('username'));
      this.cartService.addItemToCart(this.product.productId, this.selectOption, sessionStorage.getItem('username')).subscribe(
        data => {
          console.log('success');
          this.result = data;
          this.router.navigate(['/myCart']);
        },
        error => console.error(error),
        () => console.log('Products Loaded')
      );
    } else {
      this.router.navigate(['/login']);
    }
  }


  lsTest() {
    let test = 'username';
    try {
      sessionStorage.getItem(test);
      if (sessionStorage.getItem(test) === null) {
        return false;
      }
      return true;
    } catch (e) {
      return false;
    }
  }

}
