import {Component, OnInit} from '@angular/core';
import {ProductService} from '@app/services/product.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  public productList;

  /*todo title*/

  constructor(private productService: ProductService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getProducts(this.route.snapshot.params.category);
  }

  getProducts(category) {
    this.productService.getFilteredProductList(category).subscribe(
      data => {
        this.productList = data;
      },
      error => console.error(error),
      () => console.log('Products Loaded')
    );
  }

  seeProduct(product) {
    let url: string;
    url = '/product/' + product.productId;
    console.log('url: ' + url);
    this.router.navigate([url]);
  }

}
