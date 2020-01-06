import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AccountServiceService} from '@app/services/account-service.service';
import {CampaignService} from '@app/services/campaign.service';

@Component({
  selector: 'app-campagin-list',
  templateUrl: './campagin-list.component.html',
  styleUrls: ['./campagin-list.component.css']
})
export class CampaginListComponent implements OnInit {

  public campaigns;
  title = 'All Accounts';

  // tslint:disable-next-line:variable-name

  constructor( private router: Router,
               private campaignService: CampaignService) {
  }

  ngOnInit() {
    if (!this.lsTestAuthentication()) {
      this.router.navigate(['']);
    }
    console.log('loading all campaigns');
    this.getAllCampaigns();
  }

  getAllCampaigns() {
    this.campaignService.getAllCampaigns().subscribe(
      data => {
        this.campaigns = data;
      },
      error => console.error(error),
      () => console.log('Campaigns Loaded')
    );
  }

  editAccount(campaign) {
    this.router.navigate(['campaign-edit/' + campaign.campaignId]);
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
      return true;
    } catch (e) {
      return false;
    }
  }
}
