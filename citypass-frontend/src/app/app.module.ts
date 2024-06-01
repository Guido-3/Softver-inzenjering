import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app/app.component';
import { HomeComponent } from './components/presentation/home/home.component';
import { ContactComponent } from './components/presentation/contact/contact.component';
import { HeaderComponent } from './components/common/header/header.component';
import { FooterComponent } from './components/common/footer/footer.component';
import { DailyPassesComponent } from './components/presentation/daily-passes/daily-passes.component';
import { BuyComponent } from './components/presentation/buy/buy.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NgxPrintModule } from 'ngx-print';

import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ContactComponent,
    HeaderComponent,
    FooterComponent,
    DailyPassesComponent,
    BuyComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, 
    ReactiveFormsModule,
    NgxPrintModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
