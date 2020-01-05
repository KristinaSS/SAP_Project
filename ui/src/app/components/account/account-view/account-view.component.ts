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
    console.log('username: ' + sessionStorage.getItem('username'));
    this.accountServiceService.getAccount(sessionStorage.getItem('username')).subscribe(
      data => {
        this.account = data;
        if (this.lsTestAuthentication()) {
          console.log('emplyee or admin');
          this.router.navigate(['account-view-emp']);
        }
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
