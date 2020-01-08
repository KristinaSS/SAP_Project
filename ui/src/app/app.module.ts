import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { AcountListComponent } from './components/account/acount-list/acount-list.component';
import { CreateAccountComponent } from './components/account/create-account/create-account.component';
import { AccountViewComponent } from './components/account/account-view/account-view.component';
import { FilterByNamePipe } from './filter-by-name.pipe';
import {LoginComponent} from './components/login';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BasicAuthHtppInterceptorService} from '@app/security/helper/basic-htpp-interceptor.service';
import { CatagoryComponent } from './components/catagory/catagory.component';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { ViewProductComponent } from './components/product/view-product/view-product.component';
import { ShoppingCartComponent } from './components/shopping-cart/shopping-cart.component';
import { CheckoutComponent } from './components/checkout/chekout/checkout.component';
import { ThankYouComponent } from './components/checkout/thank-you/thank-you.component';
import { ProductViewEmployeeComponent } from './components/product/product-view-employee/product-view-employee.component';
import { EditProductComponent } from './components/product/edit-product/edit-product.component';
import { CreateProductComponent } from './components/product/create-product/create-product.component';
import { ProductDeletedComponent } from './components/product/product-deleted/product-deleted.component';
import { CreatedComponent } from './components/product/created/created.component';
import { UpdatedComponent } from './components/product/updated/updated.component';
import { AccountViewEmpComponent } from './components/account/account-view-emp/account-view-emp.component';
import { EditAccountComponent } from './components/account/edit-account/edit-account.component';
import { EditAccountEmpComponent } from './components/account/edit-account-emp/edit-account-emp.component';
import { CampaginListComponent } from './components/campaign/campagin-list/campagin-list.component';
import { CreateCampaignComponent } from './components/campaign/create-campaign/create-campaign.component';
import { EditCampaignComponent } from './components/campaign/edit-campaign/edit-campaign.component';
import { AddProductToCampaignComponent } from './components/campaign/add-product-to-campaign/add-product-to-campaign.component';
import { SalesReportComponent } from './components/sales-report/sales-report.component';
import { ProductListKeywordComponent } from './components/product/product-list-keyword/product-list-keyword.component';
import { ProductListOutOfStockComponent } from './components/product/product-list-out-of-stock/product-list-out-of-stock.component';
import { EditProductInCampaignComponent } from './components/campaign/edit-product-in-campaign/edit-product-in-campaign.component';
import { SeeProductsInCampaignComponent } from './components/campaign/see-products-in-campaign/see-products-in-campaign.component';
import { ProductListSaleComponent } from './components/product/product-list-sale/product-list-sale.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    AcountListComponent,
    CreateAccountComponent,
    AccountViewComponent,
    FilterByNamePipe,
    LoginComponent,
    CatagoryComponent,
    ProductListComponent,
    ViewProductComponent,
    ShoppingCartComponent,
    CheckoutComponent,
    ThankYouComponent,
    ProductViewEmployeeComponent,
    EditProductComponent,
    CreateProductComponent,
    ProductDeletedComponent,
    CreatedComponent,
    UpdatedComponent,
    AccountViewEmpComponent,
    EditAccountComponent,
    EditAccountEmpComponent,
    CampaginListComponent,
    CreateCampaignComponent,
    EditCampaignComponent,
    AddProductToCampaignComponent,
    SalesReportComponent,
    ProductListKeywordComponent,
    ProductListOutOfStockComponent,
    EditProductInCampaignComponent,
    SeeProductsInCampaignComponent,
    ProductListSaleComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [ {
    provide: HTTP_INTERCEPTORS,
    useClass: BasicAuthHtppInterceptorService, multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
