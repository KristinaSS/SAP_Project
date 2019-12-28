import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AccountServiceService} from '../../../services/account-service.service';
import {AccountTypeServiceService} from '../../../services/account-type-service.service';
import {AuthenticationService} from '@app/security/helper/authentication.service';

@Component({
  selector: 'app-account-view',
  templateUrl: './account-view.component.html',
  styleUrls: ['./account-view.component.css']
})
export class AccountViewComponent implements OnInit {

  public account;

  constructor(private accountServiceService: AccountServiceService,
              private router: Router,
              private logOutService: AuthenticationService
  ) {
  }

  ngOnInit() {
    this.getAccountByEmail();
  }

  getAccountByEmail() {
    this.accountServiceService.getAccount(sessionStorage.getItem('username')).subscribe(
      data => {
        this.account = data;
      },
      error => console.error(error),
      () => console.log('Account Loaded')
    );
  }

  logout() {
    this.logOutService.logOut();
    this.router.navigate(['/home']);
  }
}
