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
import { CommonModule } from '@angular/common';
import { SightsComponent } from './components/admin/sights/sights.component';
import { CreateSightComponent } from './components/admin/create-sight/create-sight.component';
import { ReservationsComponent } from './components/admin/reservations/reservations.component';
import { EditSightComponent } from './components/admin/edit-sight/edit-sight.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { SightsService } from './services/sights.service';


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
    RegisterComponent,
    SightsComponent,
    CreateSightComponent,
    ReservationsComponent,
    EditSightComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [SightsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
