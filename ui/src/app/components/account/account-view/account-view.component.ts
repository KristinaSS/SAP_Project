import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {AccountServiceService} from '../../../services/account-service.service';
import {AccountTypeServiceService} from '../../../services/account-type-service.service';

@Component({
  selector: 'app-account-view',
  templateUrl: './account-view.component.html',
  styleUrls: ['./account-view.component.css']
})
export class AccountViewComponent implements OnInit {

  public account;
  public accountType;

  constructor(private accountServiceService: AccountServiceService,
              private accountTypeService: AccountTypeServiceService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getFoodPlaceByID(this.route.snapshot.params.id);
  }

  getFoodPlaceByID(id: number) {
    this.accountServiceService.getAccount(id).subscribe(
      data => {
        this.account = data;
      },
      error => console.error(error),
      () => console.log('Account Loaded')
    );

  }
  deleteAccount() {
    this.accountServiceService.deleteAccount(this.account.accID).subscribe(
      data => {
        this.account = data;
      },
      error => console.error(error),
      () => console.log('Account Deleted')
    );
  }
}
