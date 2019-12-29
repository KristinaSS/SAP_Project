import {Component, OnInit} from '@angular/core';
import {ProductService} from '@app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  public productList;

  /*todo title*/

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.getProducts();
  }

  getProducts() {
    this.productService.getFilteredProductList(/*sessionStorage.getItem('category')*/'IT').subscribe(
      data => {
        this.productList = data;
      },
      error => console.error(error),
      () => console.log('Products Loaded')
    );
  }

}
