import { Component, OnInit } from '@angular/core';
import {ProductService} from '@app/services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {CampaignService} from '@app/services/campaign.service';

@Component({
  selector: 'app-product-list-sale',
  templateUrl: './product-list-sale.component.html',
  styleUrls: ['./product-list-sale.component.css']
})
export class ProductListSaleComponent implements OnInit {
  public productList;
  public campaign;

  /*todo title*/

  constructor(private productService: ProductService,
              private campaignService: CampaignService,
              private router: Router) {
  }

  ngOnInit() {
    this.getProducts();
    this.campaignService.getActiveCampaign().subscribe(
      data => {
        this.campaign = data;
      },
      error => console.error(error),
      () => console.log('Message Loaded')
    );
  }

  getProducts() {
    this.productService.getProductListByCampaign('null').subscribe(
      data => {
        this.productList = data;
      },
      error => console.error(error),
      () => console.log('Products Loaded')
    );
  }

  seeProduct(product) {
    let url: string;
    if (this.lsTestAuthentication()) {
      url = '/product-em/' + product.productId;
      this.router.navigate([url]);
    } else {
      url = '/product/' + product.productId;
      console.log('url: ' + url);
      this.router.navigate([url]);
    }
  }

  lsTestAuthentication() {
    let test = 'accountType';
    try {
      if (sessionStorage.getItem(test) === 'employee') {
        return true;
      }
      if (sessionStorage.getItem(test) === 'admin') {
        return true;
      }
      if (sessionStorage.getItem(test) === null) {
        return false;
      }
      console.log('true' + sessionStorage.getItem('accountType'));
      return false;
    } catch (e) {
      return false;
    }
  }

}
