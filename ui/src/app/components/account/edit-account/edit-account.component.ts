import { Component, OnInit } from '@angular/core';
import {Account} from '@app/components/account/account-model/account';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AccountServiceService} from '@app/services/account-service.service';
import {AccountTypeServiceService} from '@app/services/account-type-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '@app/security/helper/authentication.service';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-edit-account',
  templateUrl: './edit-account.component.html',
  styleUrls: ['./edit-account.component.css']
})
export class EditAccountComponent implements OnInit {

  validMessage = 'Ready to Join? Create a New Account';

  public account;
  private result;

  private id;
  password: string;
  passwordAgain: string;
  name: string;
  email: string;
  form_signup: FormGroup;

  constructor(private accountServiceService: AccountServiceService,
              private accountTypeServiceService: AccountTypeServiceService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.accountServiceService.getAccount(this.route.snapshot.params.username).subscribe(
      data => {
        this.result = data;
      },
      error => console.error(error),
      () => console.log('Account Loaded')
    );
    this.form_signup = new FormGroup({
        name: new FormControl('', Validators.minLength(0)),
        email: new FormControl('', Validators.minLength(0)),
        password: new FormControl('', Validators.minLength(0)),
        passwordAgain: new FormControl('', Validators.minLength(0))
      }
    );
  }

  submitChanges() {
    this.intializeMembers();
    if (this.validation()) {
      this.accountServiceService.updateAccountByUser(this.id, this.email, this.name, this.password).subscribe(
        data => {
          console.log('account created');
          this.form_signup.reset();
          this.router.navigate(['']);
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
    this.id = this.result.accID;
    this.name = this.form_signup.get('name').value;
    this.email = this.form_signup.get('email').value;
    this.password = this.form_signup.get('password').value;
    this.passwordAgain = this.form_signup.get('passwordAgain').value;
  }

  validation() {
    if (!this.email.match('[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}')) {
      if (this.email.length === 0 ) {
        this.email = this.result.email;
      } else {
        this.validMessage = 'Not valid email';
        return false;
      }
    }
    if (this.password !== this.passwordAgain) {
      this.validMessage = 'Passwords do not match';
      return false;
    }
    if (this.password.length === 0) {
      this.password = ' ';
    }
    if (this.name.length === 0) {
      this.name = this.result.name;
    }
    console.log('Validated');
    return true;
  }

  navigateCancel() {
    this.router.navigate(['']);
  }
}
