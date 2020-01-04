import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  getFilteredProductList(category: string) {
    console.log('category: ' + category);
    return this.http.post('server/product/filteredByCategory', category);
  }

  getProduct(id: string) {
    console.log('id: ' + id);
    return this.http.post('server/product/get', id);
  }

  editProduct(id, name , quantity, price, description, categoryName) {
    console.log('id: edit product ');
    return this.http.post('server/product/edit', {id,  name, quantity, price, description, categoryName});
  }

  createProduct(name , quantity, price, description, categoryName) {
    let id = null;
    console.log('id: create Product ');
    return this.http.post('server/product/create', {id, name, quantity, price, description, categoryName});
  }
  deleteProduct(id) {
    console.log('delete id: ' + id);
    return this.http.post('server/product/delete', id);
  }
}
