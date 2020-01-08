import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {CampaignService} from '@app/services/campaign.service';
import {ProductService} from '@app/services/product.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-see-products-in-campaign',
  templateUrl: './see-products-in-campaign.component.html',
  styleUrls: ['./see-products-in-campaign.component.css']
})
export class SeeProductsInCampaignComponent implements OnInit {

  private campaign;
  private products;

  validMessage: string = 'creating campaign: ';

  constructor(private router: Router,
              private  campaignServices: CampaignService,
              private route: ActivatedRoute,
              private productService: ProductService,
              private campaignService: CampaignService) {
  }

  ngOnInit() {
    if (!this.lsTestAuthentication()) {
      this.router.navigate(['']);
    }
    this.getCampaignById(this.route.snapshot.params.campaign);
  }

  getProductsInCampaign(name) {
    console.log('in method: getProductsInCampaign');
    this.productService.getProductListByCampaign(name).subscribe(
      data => {
        this.products = data;
      },
      error => console.error(error),
      () => console.log('Products Loaded')
    );
  }

  getCampaignById(id) {
    this.campaignServices.getCampaignById(id).subscribe(
      data => {
        this.campaign = data;
        console.log('campaign: ' + this.campaign.name);

        this.getProductsInCampaign(this.campaign.name);
      },
      error => console.error(error),
      () => console.log('Campaign Loaded')
    );
  }

  lsTestAuthentication() {
    let test = 'accountType';
    try {
      if (sessionStorage.getItem(test) === 'employee') {
        console.log('true empoyee');
        return true;
      }
      if (sessionStorage.getItem(test) === 'admin') {
        return true;
      }
      if (sessionStorage.getItem(test) === null) {
        return false;
      }
      return false;
    } catch (e) {
      return false;
    }
  }

  deleteProductFromCampaign(product) {
    console.log('info: ' + this.campaign.name + product.productId);
    this.campaignService.deleteProductFromCampaign(product.productId, this.campaign.name).subscribe();
    this.router.navigate(['/campaign-list']);
  }
}
