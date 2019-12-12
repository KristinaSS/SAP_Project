import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {

  constructor(private http: HttpClient) { }

  getAllAccounts() {
    return this.http.get('server/account/all');
  }
  getAccount(id: number) {
    return this.http.get('server/account/get-' + id);
  }
  createAccount(foodPlace, accountTypeId: string) {
    let body = JSON.stringify(foodPlace);
    console.log(body);
    let accID: string;
    accID = accountTypeId.substring(1, (accountTypeId.length) - 1).trim();
    let url = 'server/account/create-' + accID;
    return this.http.post(url, body, httpOptions);
  }
  deleteAccount(id: number) {
    return this.http.delete('server/account/delete-' + id);
  }
}
