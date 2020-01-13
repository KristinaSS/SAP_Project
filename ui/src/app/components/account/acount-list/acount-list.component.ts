import {Component, OnInit} from '@angular/core';

import {AccountServiceService} from '@app/services/account-service.service';
import {Router} from '@angular/router';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-acount-list',
  templateUrl: './acount-list.component.html',
  styleUrls: ['./acount-list.component.css']
})
export class AcountListComponent implements OnInit {
  public accounts;
  public filteredAccounts: any[];
  title = 'All Accounts';

  validMessage = "";

  // tslint:disable-next-line:variable-name
  private _listFilter: string;
  get listFilter(): string {
    return this._listFilter;
  }

  set listFilter(value: string) {
    this._listFilter = value;
    this.filteredAccounts = this.listFilter ? this.performFilter(this.listFilter) : this.accounts;
  }

  constructor( private router: Router,
               private accountService: AccountServiceService) {
  }

  ngOnInit() {
    if (!this.lsTestAuthentication()) {
      this.router.navigate(['']);
    }
    console.log('loading all accounts');
    this.getAllAccounts();
  }

  getAllAccounts() {
    this.accountService.getAllAccounts().subscribe(
      data => {
        this.accounts = data;
        this.filteredAccounts = this.accounts;
      },
      error => {
        this.validMessage = 'There are no accounts.';
        return throwError(error.message || error);
      }
    );
  }

  private performFilter(filterBy: string): any[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.accounts.filter((foodPace: any) => foodPace.firstName.toLocaleLowerCase().indexOf(filterBy) !== -1 ||
      foodPace.lastName.toLocaleLowerCase().indexOf(filterBy) !== -1 ||
      foodPace.email.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }
  editAccount(account) {
    this.router.navigate(['account-edit-emp/' + account.email]);
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
