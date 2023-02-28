import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MenuComponent } from './menu/menu.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { HomeComponent } from './home/home.component';
import { CatsComponent } from './cats/cats.component';
import { UsersComponent } from './users/users.component';
import { SearchComponent } from './search/search.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {MAT_DATE_LOCALE} from "@angular/material/core";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthInterceptor} from "./interceptors/auth.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    HomeComponent,
    CatsComponent,
    UsersComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    NgbModule
  ],
  providers: [{provide: MAT_DATE_LOCALE, useValue: 'de-AT'},
    {
      provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor,
      multi: true
    },],
  bootstrap: [AppComponent]
})
export class AppModule { }
