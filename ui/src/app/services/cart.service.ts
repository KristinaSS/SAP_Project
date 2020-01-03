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

  addItemToCart(productId, quantity, username) {
    console.log('username; ' + username);
    return this.http.post('server/cart/addItem', {username, productId, quantity});
  }

  calculate(username) {
    console.log('calculate username; ' + username);
    return this.http.post('server/cart/calculate', username);
  }

  delete(productId, quantity, username) {
    console.log('username; delete ' + username);
    return this.http.post('server/cart/deleteItem', {username, productId, quantity}).subscribe();
  }
}
