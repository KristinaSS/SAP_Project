import {Directive, Output, EventEmitter, Input, SimpleChange, Component, OnInit} from '@angular/core';
import {CartService} from '@app/services/cart.service';
import {AccountServiceService} from '@app/services/account-service.service';
import {ProductService} from '@app/services/product.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  @Output() onCreate: EventEmitter<any> = new EventEmitter<any>();
  public product;
  public cartList;

  constructor(private cartService: CartService,
              private productService: ProductService) {
  }

  ngOnInit() {
    this.getCartItems();
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

  getProduct(id) {
    console.log('getting product');
    this.productService.getProduct(id);
  }

}
