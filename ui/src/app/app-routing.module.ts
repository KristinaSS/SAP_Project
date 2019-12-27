import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {HomeComponent} from './components/home/home.component';
import {AcountListComponent} from './components/account/acount-list/acount-list.component';
import {AccountViewComponent} from './components/account/account-view/account-view.component';
import {CreateAccountComponent} from './components/account/create-account/create-account.component';
import {LoginComponent} from './security/login';
import {AuthGaurdService} from './security/helper/auth.guard';


/*const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'account/account-list',
    component: AcountListComponent
  },
  {
    path: 'account/create',
    component: CreateAccountComponent
  },
  {
    path: 'account/:id',
    component: AccountViewComponent
  },
  {
  path: 'register',
    component: CreateAccountComponent
  },
  {
    path: '/login',
    component: LoginComponent
  }
  ];*/

const routes: Routes = [
  {
    path: 'account/account-list',
    component: AcountListComponent,
    canActivate: [AuthGaurdService]
  },
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: CreateAccountComponent
  },
  // otherwise redirect to home
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
