import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) {
  }

  getCartItems() {
    console.log('category: ' + sessionStorage.getItem('username'));
    let username: string;
    username = sessionStorage.getItem('username');
    return this.http.post('server/cart/getAllByUser', {username});
  }
}
