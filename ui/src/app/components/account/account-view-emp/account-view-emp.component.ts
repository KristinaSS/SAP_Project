import {Component, OnInit} from '@angular/core';
import {AccountServiceService} from '@app/services/account-service.service';
import {Router} from '@angular/router';
import {AuthenticationService} from '@app/security/helper/authentication.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-account-view-emp',
  templateUrl: './account-view-emp.component.html',
  styleUrls: ['./account-view-emp.component.css']
})
export class AccountViewEmpComponent implements OnInit {


  public account;
  reportTimeFormGroup: FormGroup;
  public reportTime;

  constructor(private accountServiceService: AccountServiceService,
              private router: Router,
              private logOutService: AuthenticationService
  ) {
  }

  ngOnInit() {
    if (!this.lsTestAuthentication()) {
      this.router.navigate(['account-view']);
    }
    console.log('ng on init');
    this.getAccountByEmail();

    this.reportTimeFormGroup = new FormGroup({
      reportTime: new FormControl('', Validators.required)
    });
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

  goToEditAccount() {
    this.router.navigate(['/account-edit/' + this.account.email]);
  }

  seeSalesReport() {
    this.router.navigate([ '/sales-report/' + this.reportTime]);
  }
}
