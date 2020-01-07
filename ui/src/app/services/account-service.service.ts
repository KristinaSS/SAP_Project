import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {

  constructor(private http: HttpClient) {
  }

  getAllAccounts() {
    return this.http.get('server/account/all');
  }

  getAccount(username: string) {
    return this.http.post('server/get', {username});
  }

  createAccount(username, password, name) {
    console.log('before post');
    return this.http.post<any>('server/signup', {username, password, name});
  }

  deleteAccount(id: number) {
    return this.http.get('server/account/delete-' + id);
  }

  updateAccount(id, username, name, accountTypeName) {
    let password = null;
    return this.http.post<any>('server/account/update', {id, username, password, name, accountTypeName});
  }
}
