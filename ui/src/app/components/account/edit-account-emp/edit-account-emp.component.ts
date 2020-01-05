import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '@app/services/product.service';
import {Observable, throwError} from 'rxjs';
import {Account} from '@app/components/account/account-model/account';
import {AccountServiceService} from '@app/services/account-service.service';
import {AccountTypeServiceService} from '@app/services/account-type-service.service';
import {AuthenticationService} from '@app/security/helper/authentication.service';

@Component({
  selector: 'app-edit-account-emp',
  templateUrl: './edit-account-emp.component.html',
  styleUrls: ['./edit-account-emp.component.css']
})
export class EditAccountEmpComponent implements OnInit {
  validMessage = 'Updating Account';
  public account;

  password: string;
  passwordAgain: string;
  name: string;
  email: string;
  private account_Type;

  form_signup: FormGroup;
  accountType: FormGroup;

  constructor(private accountServiceService: AccountServiceService,
              private accountTypeServiceService: AccountTypeServiceService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    console.log('enter ng on init');
    this.account = this.accountServiceService.getAccount(this.route.snapshot.params.username).subscribe(
      data => {
        this.account = data;
        sessionStorage.setItem('accountType', this.account.accountType.name);
        console.log('account type' + sessionStorage.getItem('accountType'));
      },
      error => console.error(error),
      () => console.log('Account Loaded')
    );
    this.form_signup = new FormGroup({
        name: new FormControl('', Validators.required),
        email: new FormControl('', Validators.required)
      }
    );
    this.accountType = new FormGroup({
      accountType: new FormControl('', Validators.required)
    });
  }

  submitRegistration() {
    this.intializeMembers();
    if (this.validation()) {
      this.accountServiceService.updateAccount(this.account.accountId, this.email, this.name, this.account_Type).subscribe(
        data => {
          console.log('account edited');
          this.form_signup.reset();
          this.router.navigate(['/account/account-list']);
          return true;
        },
        error => {
          console.log('error thrown');
          this.validMessage = 'A user with this username already exists.';
          return throwError(error.message || error);
        }
      );

    }
  }

  deleteAccount(account) {
    this.accountServiceService.deleteAccount(account.accountId);

  }

  intializeMembers() {
    this.name = this.form_signup.get('name').value;
    this.email = this.form_signup.get('email').value;

    let name: string;
    name = this.email;
    console.log('this email: ' + name);
    localStorage.setItem('cart', name);
  }

  validation() {
    if (!this.email.match('/^[a-zA-Z]+ [a-zA-Z]+$/')) {
      if (this.email.length === 0 ) {
        this.email = this.account.email;
      } else {
        this.validMessage = 'Not valid email';
        return false;
      }
    }
    if (!this.name.match('[A-Z][a-z]{2,20}')) {
      if ( this.name.length === 0 ) {
        this.name = this.account.name;
      } else {

        this.validMessage = 'Not valid name';
        return false;
      }
    }
    console.log('Validated');
    return true;
  }
}
