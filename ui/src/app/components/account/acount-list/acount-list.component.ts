import {Component, OnInit} from '@angular/core';

import {AccountServiceService} from '../../../services/account-service.service';

@Component({
  selector: 'app-acount-list',
  templateUrl: './acount-list.component.html',
  styleUrls: ['./acount-list.component.css']
})
export class AcountListComponent implements OnInit {
  public accounts;
  public filteredAccounts: any[];
  title = 'All Accounts';

  // tslint:disable-next-line:variable-name
  private _listFilter: string;
  get listFilter(): string {
    return this._listFilter;
  }

  set listFilter(value: string) {
    this._listFilter = value;
    this.filteredAccounts = this.listFilter ? this.performFilter(this.listFilter) : this.accounts;
  }

  constructor(private accountService: AccountServiceService) {
  }

  ngOnInit() {
    this.getAllAccounts();
  }

  getAllAccounts() {
    this.accountService.getAllAccounts().subscribe(
      data => {
        this.accounts = data;
        this.filteredAccounts = this.accounts;
      },
      error => console.error(error),
      () => console.log('Accounts Loaded')
    );
  }

  private performFilter(filterBy: string): any[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.accounts.filter((foodPace: any) => foodPace.firstName.toLocaleLowerCase().indexOf(filterBy) !== -1 ||
      foodPace.lastName.toLocaleLowerCase().indexOf(filterBy) !== -1 ||
      foodPace.email.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }
}
