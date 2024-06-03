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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPrintModule } from 'ngx-print';
import { Header2Component } from './components/common/header2/header2.component';
import { NavbarComponent } from './components/common/navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { SightsComponent } from './components/admin/sights/sights.component';
import { CreateSightComponent } from './components/admin/create-sight/create-sight.component';
import { ReservationsComponent } from './components/admin/reservations/reservations.component';
import { EditSightComponent } from './components/admin/edit-sight/edit-sight.component';
import { CommonModule } from '@angular/common';
import { SightsService } from './services/sights.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ContactComponent,
    Header2Component,
    FooterComponent,
    DailyPassesComponent,
    BuyComponent,
    LoginComponent,
    RegisterComponent, 
    SightsComponent,
    CreateSightComponent,
    ReservationsComponent,
    EditSightComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, 
    ReactiveFormsModule,
    NgxPrintModule,
    FormsModule,
    CommonModule
  ],
  providers: [SightsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
