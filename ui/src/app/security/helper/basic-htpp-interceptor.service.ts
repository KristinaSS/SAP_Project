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

    console.log('authorize interceptor');
    request = request.clone({headers: request.headers.set('Authorization', sessionStorage.getItem('token'))});

    return next.handle(request);
  }
}
