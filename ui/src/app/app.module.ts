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
import {LoginComponent} from './security/login';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BasicAuthHtppInterceptorService} from '@app/security/helper/basic-htpp-interceptor.service';
import { CatagoryComponent } from './components/catagory/catagory.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ViewProductComponent } from './components/view-product/view-product.component';
import { ShoppingCartComponent } from './components/shopping-cart/shopping-cart.component';


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
    ShoppingCartComponent
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
