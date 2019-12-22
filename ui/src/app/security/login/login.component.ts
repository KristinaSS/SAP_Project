import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../helper/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  invalidLogin = false;

  constructor(private router: Router,
              private loginservice: AuthenticationService) { }

  ngOnInit() {
  }

  checkLogin() {
    console.log('enter method');
    (this.loginservice.authenticate(this.username, this.password).subscribe(
        data => {
          this.router.navigate(['account/account-list']);
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

}
