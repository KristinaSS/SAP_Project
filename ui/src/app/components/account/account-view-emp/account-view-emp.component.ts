import {Component, OnInit} from '@angular/core';
import {AccountServiceService} from '@app/services/account-service.service';
import {Router} from '@angular/router';
import {AuthenticationService} from '@app/security/helper/authentication.service';

@Component({
  selector: 'app-account-view-emp',
  templateUrl: './account-view-emp.component.html',
  styleUrls: ['./account-view-emp.component.css']
})
export class AccountViewEmpComponent implements OnInit {


  public account;

  constructor(private accountServiceService: AccountServiceService,
              private router: Router,
              private logOutService: AuthenticationService
  ) {
  }

  ngOnInit() {
    console.log('ng on init');
    this.getAccountByEmail();
  }

  getAccountByEmail() {
    console.log('username:  in employee' + sessionStorage.getItem('username'));
    this.accountServiceService.getAccount(sessionStorage.getItem('username')).subscribe(
      data => {
        this.account = data;
        sessionStorage.setItem('accountType', this.account.accountType.name);
        console.log('account type' + sessionStorage.getItem('accountType'));
      },
      error => console.error(error),
      () => console.log('Account Loaded')
    );
  }

  logout() {
    this.logOutService.logOut();
    this.router.navigate(['']);
  }
  getAllAccounts() {
    console.log('Account Loaded in method');
    this.router.navigate(['account/account-list']);
  }
}
