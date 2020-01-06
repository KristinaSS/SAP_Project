import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CartService} from '@app/services/cart.service';
import {ProductService} from '@app/services/product.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sales-report',
  templateUrl: './sales-report.component.html',
  styleUrls: ['./sales-report.component.css']
})
export class SalesReportComponent implements OnInit {
  @Output() onCreate: EventEmitter<any> = new EventEmitter<any>();
  public product;
  public reportItems;
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
        this.reportItems = data;
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
}
