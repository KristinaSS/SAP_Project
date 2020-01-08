import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CampaignService {

  constructor(private http: HttpClient) { }

  getAllCampaigns() {
    return this.http.get('server/campaign/all');
  }

  createCampaign(name, details, isActive) {
    let id  = null;
    return this.http.post('server/campaign/create', {id, name, details, isActive});
  }

  getCampaignById(id) {
    return this.http.post('server/campaign/get', id);
  }

  editCampaign(id: any, name: any, details: any, isActive: any) {
    return this.http.post('server/campaign/edit', {id, name, details, isActive});
  }

  addProductToCampaign(productId, campaignName, price) {
    return this.http.post('server/campaign/addToCampaign', {productId, campaignName, price});
  }

  deleteProductFromCampaign(productId: any, campaignName: any) {
    let price = null;
    console.log('info:  ' + productId + campaignName);
    return this.http.post('server/campaign/deleteProductInCampaign', {productId, campaignName, price});
  }
}
