import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountTypeServiceService {
  constructor(private http: HttpClient) { }

  getAllAccountTypes() {
    return this.http.get('server/all-account-types');
  }
  getAccountType(id: number) {
    return this.http.get('server//accountType-' + id);
  }
}
