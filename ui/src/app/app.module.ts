import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import {FoodPlaceService} from './services/food-place.service';
import { DiningPlacesListComponent } from './components/product/product-list/dining-places-list.component';
import {AppRoutingModule} from './app-routing.module';
import { HomeComponent } from './components/home/home.component';
import { ViewFoodPlaceComponent } from './components/product/view-product/view-food-place.component';
import { CreateFoodPlaceComponent } from './components/product/create-product/create-food-place.component';
import { HeaderComponent } from './components/header/header.component';
import { AcountListComponent } from './components/account/acount-list/acount-list.component';
import { CreateAccountComponent } from './components/account/create-account/create-account.component';
import { AccountViewComponent } from './components/account/account-view/account-view.component';
import { FilterByNamePipe } from './filter-by-name.pipe';
import { LoginComponent } from './login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    DiningPlacesListComponent,
    HomeComponent,
    ViewFoodPlaceComponent,
    CreateFoodPlaceComponent,
    HeaderComponent,
    AcountListComponent,
    CreateAccountComponent,
    AccountViewComponent,
    FilterByNamePipe,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [FoodPlaceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
