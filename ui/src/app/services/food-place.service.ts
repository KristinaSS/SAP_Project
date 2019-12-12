import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class FoodPlaceService {

  constructor(private http: HttpClient) { }

  getAllFoodPlaces() {
    return this.http.get('server/api/v1/dining/diningPlaces');
  }
  getFoodPlace(id: number) {
    return this.http.get('server/api/v1/dining/get-diningPlace-' + id);
  }
  createFoodPlace(foodPlace) {
    let body = JSON.stringify(foodPlace);
    return this.http.post('server/api/v1/dining/createDiningPlace', body, httpOptions);
  }
  deleteFoodPlace(foodPlaceId: number){
    return this.http.delete('server/api/v1/dining/delete-diningPlace-' + foodPlaceId);
  }
}
