import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../helper/authentication.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  invalidLogin = false;
  private form_signin: FormGroup;

  constructor(private router: Router,
              private loginservice: AuthenticationService) {
  }

  ngOnInit() {
    this.form_signin = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  checkLogin() {
    this.username = this.form_signin.get('username').value;
    this.password = this.form_signin.get('password').value;
    console.log('enter method' + this.username + this.password);
    (this.loginservice.authenticate(this.username, this.password).subscribe(
        data => {
          this.router.navigate(['account-view']);
          this.invalidLogin = false;
          console.log('Logged in');
        },
        error => {
          this.invalidLogin = true;
          console.log('Problems... again');
        }
      )
    );
  }

  submitCart() {
    console.log('item: ' + sessionStorage.getItem('cart'));
    this.loginservice.createCart(sessionStorage.getItem('cart')).subscribe(
      data => {
        console.log('cart created');
        return true;
      },
      error => {
        console.log('error thrown');
        return throwError(error.message || error);
      }
    );
  }
/*
  isAccountCreated() {
    let result: boolean;
    result = this.lsTest();
    console.log('result: ' + result);
    if (result) {
      this.submitCart();
      sessionStorage.removeItem('cart');
    }
  }

  lsTest() {
    let test = 'cart';
    try {
      sessionStorage.getItem(test);
      return true;
    } catch (e) {
      return false;
    }
  }*/
}
