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
    console.log('id from route: ' + this.route.snapshot.params.id);
    this.getProductByID(this.route.snapshot.params.id);
/*    console.log(this.product.discription);*/
  }

  getProductByID(id) {
    this.productService.getProduct(id).subscribe(
      data => {
        this.product = data;
      },
      error => console.error(error),
      () => console.log('Account Loaded')
    );
  }

}
