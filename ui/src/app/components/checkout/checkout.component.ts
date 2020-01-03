import {Component, OnInit} from '@angular/core';
import {CartService} from '@app/services/cart.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  public country;
  public address;
  public city;
  public state;
  public postCode;
  public phoneNumber;
  public cardType;
  public cardNumber;
  public CVV;
  public expirationMonth;
  public expirationYear;

  public cartList;
  public sum;
  public total;

  constructor(private cartService: CartService) {
  }

  ngOnInit() {
    this.getCartItems();
    this.calculate();
    this.total = this.total + 5;
  }

  public getTotal() {
    this.total =  this.sum + 5;
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
}
