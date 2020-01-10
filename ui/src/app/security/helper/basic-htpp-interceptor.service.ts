import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from '@angular/common/http';
import {AuthenticationService} from './authentication.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthHtppInterceptorService implements HttpInterceptor {


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('enter interceptor');

    if (request.url.includes('login')) {
      return next.handle(request);
    }
    if (request.url.includes('signup')) {
      return next.handle(request);
    }
    if (request.url.includes('/product/filteredByCategory')) {
      return next.handle(request);
    }
    if (request.url.includes('product/get')) {
      return next.handle(request);
    }
    if (request.url.includes('cart')) {
      return next.handle(request);
    }
    if (request.url.includes('cart/addItem')) {
      return next.handle(request);
    }
    if (request.url.includes('cart/calculate')) {
      return next.handle(request);
    }
    if (request.url.includes('cart/deleteItem')) {
      return next.handle(request);
    }
    if (request.url.includes('order/makeOrder')) {
      return next.handle(request);
    }
    if (request.url.includes('product/edit')) {
      return next.handle(request);
    }
    if (request.url.includes('product/create')) {
      return next.handle(request);
    }
    if (request.url.includes('product/delete')) {
      return next.handle(request);
    }
    if (request.url.includes('account/all')) {
      return next.handle(request);
    }
    if (request.url.includes('account/update')) {
      return next.handle(request);
    }
    if (request.url.includes('campaign/all')) {
      return next.handle(request);
    }
    if (request.url.includes('campaign/create')) {
      return next.handle(request);
    }
    if (request.url.includes('product/outOdStock')) {
      return next.handle(request);
    }
    if (request.url.includes('product/filteredByKeyword')) {
      return next.handle(request);
    }
    if (request.url.includes('orderDetails/report')) {
      return next.handle(request);
    }
    if (request.url.includes('product/calculate')) {
      return next.handle(request);
    }
    if (request.url.includes('product/calculate-with-shipping')) {
      return next.handle(request);
    }
    if (request.url.includes('account/delete-{accID}')) {
      return next.handle(request);
    }
    if (request.url.includes('campaign/addToCampaign')) {
      return next.handle(request);
    }
    if (request.url.includes('campaign/deleteProductInCampaign')) {
      return next.handle(request);
    }
    if (request.url.includes('product/filteredByCampaign')) {
      return next.handle(request);
    }
    if (request.url.includes('campaign/active')) {
      return next.handle(request);
    }


    console.log('authorize interceptor');
    request = request.clone({headers: request.headers.set('Authorization', sessionStorage.getItem('token'))});

    return next.handle(request);
  }
}
