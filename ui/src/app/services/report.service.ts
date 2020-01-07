import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }

  calculate() {
    return this.http.get('server/orderDetails/calculate');
  }
  getSalesReport() {
    return this.http.get('server/orderDetails/report');
  }
}
