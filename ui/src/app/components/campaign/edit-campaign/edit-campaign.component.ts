import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '@app/services/product.service';
import {Observable} from 'rxjs';
import {CampaignService} from '@app/services/campaign.service';

@Component({
  selector: 'app-edit-campaign',
  templateUrl: './edit-campaign.component.html',
  styleUrls: ['./edit-campaign.component.css']
})
export class EditCampaignComponent implements OnInit {

  campaignFormGroup: FormGroup;
  booleanformGroup: FormGroup;
  private name;
  private description;
  private isActive;
  private campaign;
  private products;

  validMessage: string = 'creating campaign: ';

  constructor(private router: Router,
              private  campaignServices: CampaignService,
              private route: ActivatedRoute,
              private productService: ProductService) {
  }

  ngOnInit() {
    if (!this.lsTestAuthentication()) {
      this.router.navigate(['']);
    }
    this.getCampaignById(this.route.snapshot.params.id);
    this.campaignFormGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });
    this.booleanformGroup = new FormGroup({
      isActive: new FormControl('', Validators.required)
    });

    this.getProductsInCampaign();
  }
  getProductsInCampaign() {
    this.productService.getProductListByCampaign(this.campaign.name).subscribe(
      data => {
        this.products = data;
      },
      error => console.error(error),
      () => console.log('Campaign Loaded')
    );
  }

  getCampaignById(id) {
    this.campaignServices.getCampaignById(id).subscribe(
      data => {
        this.campaign = data;
        console.log('campaign: ' + this.campaign.name);
      },
      error => console.error(error),
      () => console.log('Campaign Loaded')
    );
  }

  submitRegistration() {
    this.intializeMembers();
    if (this.validation()) {
      this.validMessage = 'Your campaign registration has been submitted. Thank you!';
      this.campaignServices.editCampaign(this.campaign.campaignId, this.name, this.description, this.isActive).subscribe(
        data => {
          this.campaignFormGroup.reset();
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      );
      this.router.navigate(['campaign-list']);
    }
  }

  intializeMembers() {
    this.name = this.campaignFormGroup.get('name').value;
    this.description = this.campaignFormGroup.get('description').value;
  }

  validation() {
    if (this.name.length === 0) {
      this.name = this.campaign.name;
    }
    if (this.description.length === 0) {
      this.description = this.campaign.details;
    }
    console.log('Validated');
    return true;
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

  seeproductsInCampaign() {
    this.router.navigate(['see-products/' + this.campaign.campaignId]);
  }
}
