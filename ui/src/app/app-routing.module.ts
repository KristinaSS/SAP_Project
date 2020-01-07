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
import {AccountViewEmpComponent} from '@app/components/account/account-view-emp/account-view-emp.component';
import {EditAccountEmpComponent} from '@app/components/account/edit-account-emp/edit-account-emp.component';
import {SalesReportComponent} from '@app/components/sales-report/sales-report.component';
import {CampaginListComponent} from '@app/components/campaign/campagin-list/campagin-list.component';
import {CreateCampaignComponent} from '@app/components/campaign/create-campaign/create-campaign.component';
import {EditCampaignComponent} from '@app/components/campaign/edit-campaign/edit-campaign.component';
import {AddProductToCampaignComponent} from '@app/components/product/add-product-to-campaign/add-product-to-campaign.component';
import {ProductListKeywordComponent} from '@app/components/product/product-list-keyword/product-list-keyword.component';
import {ProductListOutOfStockComponent} from '@app/components/product/product-list-out-of-stock/product-list-out-of-stock.component';

const routes: Routes = [
  {
    path: 'sales-report',
    component: SalesReportComponent
  },
  {
    path: 'campaign-list',
    component: CampaginListComponent
  },
  {
    path: 'campaign-create',
    component: CreateCampaignComponent
  },
  {
    path: 'campaign-edit/:id',
    component: EditCampaignComponent
  },
  {
    path: 'product-add-to-campaign/:id',
    component: AddProductToCampaignComponent
  },
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
    path: 'product/:id',
    component: ViewProductComponent
  },
  {
    path: 'product-em/:id',
    component: ProductViewEmployeeComponent
  },
  {
    path: 'product-list/:category',
    component: ProductListComponent
  },
  {
    path: 'product-list-key/:keyword',
    component: ProductListKeywordComponent
  },
  {
    path: 'product-list-not-in-stock',
    component: ProductListOutOfStockComponent
  },
  {
    path: 'myCart',
    component: ShoppingCartComponent,
    canActivate: [AuthGaurdService]
  },
  {
    path: 'account/account-list',
    component: AcountListComponent
  },
  {
    path: 'account-view',
    component: AccountViewComponent,
    canActivate: [AuthGaurdService]
  },
  {
    path: 'account-view-emp',
    component: AccountViewEmpComponent,
    canActivate: [AuthGaurdService]
  },
  {
    path: 'account-edit-emp/:username',
    component: EditAccountEmpComponent,
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
  {
    path: '',
    component: HomeComponent
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
