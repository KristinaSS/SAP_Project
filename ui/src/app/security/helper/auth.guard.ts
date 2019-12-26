import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {AuthenticationService} from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGaurdService implements CanActivate {

  constructor(private router: Router,
              private authService: AuthenticationService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    console.log('is logged in: ' + this.authService.isUserLoggedIn());
    if (this.authService.isUserLoggedIn()) {
      console.log('should activate');
      return true;
    }

    this.router.navigate(['login']);
    return false;

  }

}

export class AuthGuard {
}
