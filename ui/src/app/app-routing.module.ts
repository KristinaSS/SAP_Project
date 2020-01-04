import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {HomeComponent} from './components/home/home.component';
import {AcountListComponent} from './components/account/acount-list/acount-list.component';
import {AccountViewComponent} from './components/account/account-view/account-view.component';
import {CreateAccountComponent} from './components/account/create-account/create-account.component';
import {LoginComponent} from './components/login';
import {AuthGaurdService} from './security/helper/auth.guard';
import {ProductListComponent} from '@app/components/product/product-list/product-list.component';
import {ViewProductComponent} from '@app/components/product/view-product/view-product.component';
import {ShoppingCartComponent} from '@app/components/shopping-cart/shopping-cart.component';
import {CheckoutComponent} from '@app/components/checkout/chekout/checkout.component';
import {ThankYouComponent} from '@app/components/checkout/thank-you/thank-you.component';
import {ProductViewEmployeeComponent} from '@app/components/product/product-view-employee/product-view-employee.component';
import {CreateProductComponent} from '@app/components/product/create-product/create-product.component';
import {EditProductComponent} from '@app/components/product/edit-product/edit-product.component';
import {ProductDeletedComponent} from '@app/components/product/product-deleted/product-deleted.component';
import {CreatedComponent} from '@app/components/product/created/created.component';
import {UpdatedComponent} from '@app/components/product/updated/updated.component';

const routes: Routes = [
  {
    path: 'product-created',
    component: CreatedComponent
  },
  {
    path: 'product-updated',
    component: UpdatedComponent
  },
  {
    path: 'product-deleted',
    component: ProductDeletedComponent
  },
  {
    path: 'product-create',
    component: CreateProductComponent
  },
  {
    path: 'product-edit/:id',
    component: EditProductComponent
  },
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
    path: 'product-em/:id',
    component: ProductViewEmployeeComponent
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
