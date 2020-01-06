import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ProductService} from '@app/services/product.service';
import {Observable} from 'rxjs';
import {CampaignService} from '@app/services/campaign.service';

@Component({
  selector: 'app-create-campaign',
  templateUrl: './create-campaign.component.html',
  styleUrls: ['./create-campaign.component.css']
})
export class CreateCampaignComponent implements OnInit {

  campaignFormGroup: FormGroup;
  booleanformGroup: FormGroup;
  private name;
  private description;
  private isActive;

  validMessage: string = 'creating campaign: ';

  constructor(private router: Router,
              private  campaignServices: CampaignService) {
  }

  ngOnInit() {
    this.campaignFormGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });
    this.booleanformGroup = new FormGroup({
      isActive: new FormControl('', Validators.required)
    });
  }

  submitRegistration() {
    this.intializeMembers();
    if (this.validation()) {
      this.validMessage = 'Your campaign registration has been submitted. Thank you!';
      this.campaignServices.createCampaign(this.name, this.description, this.isActive).subscribe(
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
      this.validMessage = 'Not valid name';
      return false;
    }
    if (this.description.length === 0) {
      this.validMessage = 'Please fill out description';
      return false;
    }
    console.log('Validated');
    return true;
  }
}
