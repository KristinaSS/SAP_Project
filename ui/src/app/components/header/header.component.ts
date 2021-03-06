import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '@app/security/helper/authentication.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ProductService} from '@app/services/product.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  constructor(private router: Router,
              private authenticationService: AuthenticationService) {
  }
  private formGroup: FormGroup;
  private keyword;

  ngOnInit() {
    this.formGroup = new FormGroup({
      search: new FormControl('', Validators.required)
    });
  }
  searchProductsByKeyword() {
    this.router.navigate(['/product-list-key/' + this.keyword]);
  }

  seeShoppingCart() {
    let result: boolean;
    result = this.lsTest();
    console.log('result: ' + result);
    if (result) {
      this.router.navigate(['/myCart']);
    } else {
      this.router.navigate(['/login']);
    }
  }

  isUserLoggedIn() {
    let result: boolean;
    result = this.lsTest();
    console.log('result: ' + result);
    if (result) {
      this.router.navigate(['/account-view']);
    } else {
      this.router.navigate(['/login']);
    }
  }

  lsTest() {
    let test = 'username';
    try {
      sessionStorage.getItem(test);
      return true;
    } catch (e) {
      return false;
    }
  }

}
