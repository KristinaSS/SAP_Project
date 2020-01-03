import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {HomeComponent} from './components/home/home.component';
import {AcountListComponent} from './components/account/acount-list/acount-list.component';
import {AccountViewComponent} from './components/account/account-view/account-view.component';
import {CreateAccountComponent} from './components/account/create-account/create-account.component';
import {LoginComponent} from './security/login';
import {AuthGaurdService} from './security/helper/auth.guard';
import {ProductListComponent} from '@app/components/product/product-list/product-list.component';
import {ViewProductComponent} from '@app/components/product/view-product/view-product.component';
import {ShoppingCartComponent} from '@app/components/shopping-cart/shopping-cart.component';
import {CheckoutComponent} from '@app/components/checkout/checkout.component';
import {ThankYouComponent} from '@app/components/thank-you/thank-you.component';

const routes: Routes = [
  {
    path: 'myCart',
    component: ShoppingCartComponent,
    canActivate: [AuthGaurdService]
  },
  {
    path: 'product/:id',
    component: ViewProductComponent
  },
  {
    path: 'account/account-list',
    component: AcountListComponent,
    canActivate: [AuthGaurdService]
  },
  {
    path: 'product-list/:category',
    component: ProductListComponent
  },
  {
    path: 'account-view',
    component: AccountViewComponent,
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
  {
    path: 'checkout',
    component: CheckoutComponent
  },
  {
    path: 'thankyou',
    component: ThankYouComponent
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
