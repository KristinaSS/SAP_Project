import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }

  calculate(time) {
    console.log('time' + time);
    return this.http.post('server/orderDetails/calculate', time);
  }
  getSalesReport(time) {
    return this.http.post('server/orderDetails/report', time);
  }
}
