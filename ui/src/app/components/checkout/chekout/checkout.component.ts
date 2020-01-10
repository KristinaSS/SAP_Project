import {Component, OnInit} from '@angular/core';
import {CartService} from '@app/services/cart.service';
import {Router} from '@angular/router';

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

  constructor(private cartService: CartService,
              private router: Router) {
  }

  ngOnInit() {
    let result: boolean;
    result = this.lsTest();
    console.log('result: ' + result);
    if (result) {
      this.getCartItems();
      this.calculate();
      this.getTotal();
    } else {
        this.router.navigate(['/login']);
    }
  }

  public getTotal() {
    this.cartService.getTotalWithShipping(sessionStorage.getItem('username')).subscribe(
      data => {
        this.total = data;
      },
      error => console.error(error),
      () => console.log('Items Loaded')
    );
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

  placeorder() {
    console.log('place order' + this.expirationMonth +
      this.expirationYear);
    this.cartService.makeorder(sessionStorage.getItem('username'),
      this.country,
      this.address,
      this.city, this.state,
      this.postCode,
      this.phoneNumber,
      this.cardType,
      this.cardNumber,
      this.CVV,
      this.expirationMonth,
      this.expirationYear);
    this.router.navigate(['thankyou']);
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
