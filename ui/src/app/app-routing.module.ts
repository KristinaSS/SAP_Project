import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {HomeComponent} from './components/home/home.component';
import {AcountListComponent} from './components/account/acount-list/acount-list.component';
import {AccountViewComponent} from './components/account/account-view/account-view.component';
import {CreateAccountComponent} from './components/account/create-account/create-account.component';
import {LoginComponent} from './security/login';
import {AuthGuard} from './security/helper';

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
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
