import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ProductService} from '@app/services/product.service';

@Component({
  selector: 'app-view-product',
  templateUrl: './view-product.component.html',
  styleUrls: ['./view-product.component.css']
})
export class ViewProductComponent implements OnInit {

  constructor(private productService: ProductService,
              private route: ActivatedRoute) {
  }

  public product;

  ngOnInit() {
    this.getProductByID();
/*    console.log(this.product.discription);*/
  }

  getProductByID() {
    this.productService.getProduct(1).subscribe(
      data => {
        this.product = data;
      },
      error => console.error(error),
      () => console.log('Account Loaded')
    );
  }

}
