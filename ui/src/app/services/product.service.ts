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

  getProduct(id: number) {
    console.log('id: ' + id);
    return this.http.post('server/product/get', id);
  }
}
