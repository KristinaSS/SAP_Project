import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable, throwError} from 'rxjs';
import {AccountServiceService} from '@app/services/account-service.service';
import {AccountTypeServiceService} from '@app/services/account-type-service.service';
import {Router} from '@angular/router';
import {Account} from '../account-model/account';
import {AuthenticationService} from '@app/security/helper/authentication.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {
  validMessage = 'Ready to Join? Create a New Account';

  account: Account;

  password: string;
  passwordAgain: string;
  first_name: string;
  last_name: string;
  email: string;
  form_signup: FormGroup;

  constructor(private accountServiceService: AccountServiceService,
              private accountTypeServiceService: AccountTypeServiceService,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.form_signup = new FormGroup({
        first_name: new FormControl('', Validators.required),
        last_name: new FormControl('', Validators.required),
        email: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required),
        passwordAgain: new FormControl('', Validators.required)
      }
    );
  }

  submitRegistration() {
    this.intializeMembers();
    if (this.validation()) {
      this.accountServiceService.createAccount(this.email, this.password, this.first_name + ' ' + this.last_name).subscribe(
        data => {
          console.log('account created');
          this.form_signup.reset();
          this.router.navigate(['/login']);
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

  intializeMembers() {
    this.first_name = this.form_signup.get('first_name').value;
    this.last_name = this.form_signup.get('last_name').value;
    this.email = this.form_signup.get('email').value;
    this.password = this.form_signup.get('password').value;
    this.passwordAgain = this.form_signup.get('passwordAgain').value;

    let name: string;
    name = this.email;
    console.log('this email: ' + name);
    localStorage.setItem('cart', name);
  }

  validation() {
    /* let result: boolean;
     result = this.accountServiceService.accountExists(this.email)._isScalar;
     console.log('result: ' + result);
     if (result) {
       this.validMessage = 'Account with this email already exists';
       return false;
     }*/
    if (this.email.length === 0 || !this.email.match('[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}')) {
      this.validMessage = 'Not valid email';
      return false;
    }
    if (this.password.length === 0 || this.password !== this.passwordAgain) {
      this.validMessage = 'Passwords do not match';
      return false;
    }
    /* if (!this.password.match('^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$')) {
          this.validMessage = 'Not valid password: must be at least 6 letters, contains a capital letter and number!';
          return false;
        }*/
    if (this.first_name.length === 0 || !this.first_name.match('[A-Z][a-z]{2,20}')) {
      this.validMessage = 'Not valid first name';
      return false;
    }
    if (this.last_name.length === 0 || !this.last_name.match('[A-Z][a-z]{2,20}')) {
      this.validMessage = 'Not valid last name';
      return false;
    }
    console.log('Validated');
    return true;
  }

  navigateCancel() {
    console.log('Navigate to login');
    this.router.navigate(['/login']);
  }
}
