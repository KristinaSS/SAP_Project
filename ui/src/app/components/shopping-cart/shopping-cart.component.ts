import {Directive, Output, EventEmitter, Input, SimpleChange, Component, OnInit} from '@angular/core';
import {CartService} from '@app/services/cart.service';
import {AccountServiceService} from '@app/services/account-service.service';
import {ProductService} from '@app/services/product.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  @Output() onCreate: EventEmitter<any> = new EventEmitter<any>();
  public product;
  public cartList;
  public sum;

  constructor(private cartService: CartService,
              private productService: ProductService,
              private router: Router) {
  }

  ngOnInit() {
    this.getCartItems();
    this.calculate();
  }

  getCartItems() {
    this.cartService.getCartItems().subscribe(
      data => {
        this.cartList = data;
      },
      error => console.error(error),
      () => console.log('Items Loaded')
    );
  }

  getProduct(item) {
    console.log('getting product');
    let id: string;
    id = item.product.productId;
    this.router.navigate(['/product/' + id]);
  }

  calculate() {
    console.log('calculate');
    this.cartService.calculate(sessionStorage.getItem('username')).subscribe(
      data => {
        this.sum = data;
      },
      error => console.error(error),
      () => console.log('Items Loaded')
    );
  }

  deleteItem(item) {
    console.log('delete 1');
    this.cartService.delete(item.product.productId, item.quantity, sessionStorage.getItem('username'));
  }

  refresh(): void {
    window.location.reload();
  }
}
