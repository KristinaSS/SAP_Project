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

  getProduct(productId, type) {
    console.log('id: ' + productId);
    return this.http.post('server/product/get', {productId, type});
  }

  editProduct(id, name , quantity, price, minPrice, description, categoryName) {
    console.log('id: edit product ');
    return this.http.post('server/product/edit', {id,  name, quantity, price, description, categoryName, minPrice});
  }

  createProduct(name , quantity, price, minPrice,  description, categoryName) {
    let id = null;
    console.log('id: create Product ');
    return this.http.post('server/product/create', {id, name, quantity, price, description, categoryName, minPrice});
  }
  deleteProduct(id) {
    console.log('delete id: ' + id);
    return this.http.post('server/product/delete', id);
  }

  getOutOfStockProductList() {
    return this.http.get('server/product/outOfStock');
  }

  getFilteredProductListByKeyword(keyword: any) {
    return this.http.post('server/product/filteredByKeyword', keyword);
  }

  getProductListByCampaign(name) {
    console.log('method: getProductListByCampaign');
    return this.http.post('server/product/filteredByCampaign', name);
  }

  getAllAvailableProducts() {
    return this.http.get('server/product/all');
  }
}
