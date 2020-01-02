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
    return this.http.delete('server/account/delete-' + id);
  }

  /*  accountExists(email): Observable<boolean> {
      let result: Observable<boolean>;
      result =  this.http.post<any>('server/findAccountByEmail', {email});
      console.log(result);
      return result;
    }*/
}
