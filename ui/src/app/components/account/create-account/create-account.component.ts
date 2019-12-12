import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {AccountServiceService} from '../../../services/account-service.service';
import {AccountTypeServiceService} from '../../../services/account-type-service.service';
import {Router} from '@angular/router';
import {Account} from '../account-model/account';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {
  title: 'Create  Account';
  accountForm: FormGroup;
  validMessage: string = '';
  password: string;
  passwordAgain: string;
  account: Account;

  /* accountTypes: AccountType[] = [
     {id: '1', name: 'client'},
     {id: '2', name: 'employee'}
   ];*/

  constructor(private accountServiceService: AccountServiceService,
              private accountTypeServiceService: AccountTypeServiceService,
              private router: Router) {
  }

  ngOnInit() {
    this.accountForm = new FormGroup({
        name: new FormControl('', Validators.required),
        email: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required),
        passwordAgain: new FormControl('', Validators.required)
      }
    );
  }

  submitRegistration() {
    this.account = new Account(this.accountForm.get('name').value,
      this.accountForm.get('email').value,
      this.accountForm.get('password').value);
    if (this.accountForm.valid && (this.accountForm.get('password').value === this.accountForm.get('passwordAgain').value)) {
      this.validMessage = 'Your account registration has been submitted. Thank you!';
      this.accountServiceService.createAccount(this.account, '1').subscribe(
        data => {
          this.accountForm.reset();
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      );
      this.router.navigate(['account/account-list']);
    } else {
      this.validMessage = 'Please fill out this form before submitting';
    }
  }
}

export interface AccountType {
  id: string;
  name: string;
}
