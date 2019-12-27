import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {AccountServiceService} from '@app/services/account-service.service';
import {AccountTypeServiceService} from '@app/services/account-type-service.service';
import {Router} from '@angular/router';
import {Account} from '../account-model/account';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {
  validMessage: string = '';

  account: Account;

  password: string;
  passwordAgain: string;
  first_name: string;
  last_name: string;
  email: string;
  form_signup: FormGroup;

  constructor(private accountServiceService: AccountServiceService,
              private accountTypeServiceService: AccountTypeServiceService,
              private router: Router) {
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
      this.account = new Account(this.first_name + ' ' + this.last_name, this.email, this.password);
      this.validMessage = 'Your account registration has been submitted. Thank you!';
      this.accountServiceService.createAccount(this.account, '1').subscribe(
        data => {
          this.form_signup.reset();
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      );
      this.router.navigate(['/login']);
    } else {
      this.validMessage = 'Please fill out this form before submitting';
    }
  }

  intializeMembers() {
    this.first_name = this.form_signup.get('first_name').value;
    this.last_name = this.form_signup.get('last_name').value;
    this.email = this.form_signup.get('email').value;
    this.password = this.form_signup.get('password').value;
    this.passwordAgain = this.form_signup.get('password_again').value;
  }

  validation() {
    if (this.password !== this.passwordAgain) {
      this.validMessage = 'Passwords do not match';
      return false;
    }
    if (!this.first_name.match('[A-Z][a-z]{2,20}')) {
      this.validMessage = 'Not valid first name';
      return false;
    }
    if (!this.last_name.match('[A-Z][a-z]{2,20}')) {
      this.validMessage = 'Not valid last name';
      return false;
    }
    if (!this.email.match('[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}')) {
      this.validMessage = 'Not valid email';
      return false;
    }
    return true;
  }
}
