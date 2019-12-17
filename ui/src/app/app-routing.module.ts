import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {DiningPlacesListComponent} from './components/product/product-list/dining-places-list.component';
import {HomeComponent} from './components/home/home.component';
import {ViewFoodPlaceComponent} from './components/product/view-product/view-food-place.component';
import {CreateFoodPlaceComponent} from './components/product/create-product/create-food-place.component';
import {AcountListComponent} from './components/account/acount-list/acount-list.component';
import {AccountViewComponent} from './components/account/account-view/account-view.component';
import {CreateAccountComponent} from './components/account/create-account/create-account.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'product-list/view-product/:id',
    component: ViewFoodPlaceComponent
  },
  {
    path: 'product-list/product',
    component: DiningPlacesListComponent
  },
  {
    path: 'dining-place/create',
    component: CreateFoodPlaceComponent
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
  }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
