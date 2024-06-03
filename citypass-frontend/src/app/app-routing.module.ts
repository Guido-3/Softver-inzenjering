import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/presentation/home/home.component';
import { ContactComponent } from './components/presentation/contact/contact.component';
import { DailyPassesComponent } from './components/presentation/daily-passes/daily-passes.component';
import { BuyComponent } from './components/presentation/buy/buy.component';
import { LoginComponent } from './components/auth/login/login.component';
import { ReservationsComponent } from './components/admin/reservations/reservations.component';
import { SightsComponent } from './components/admin/sights/sights.component';
import { CreateSightComponent } from './components/admin/create-sight/create-sight.component';
import { EditSightComponent } from './components/admin/edit-sight/edit-sight.component';
import { HttpClientModule } from '@angular/common/http';
// import { RegisterComponent } from './components/auth/register/register.component';

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "about", component: ContactComponent },
  { path: "daily-passes", component: DailyPassesComponent},
  { path: "buy", component: BuyComponent},
  { path: "admin/reservations", component: ReservationsComponent},
  { path: "admin/sights", component: SightsComponent},
  { path: "admin/sights/create", component: CreateSightComponent},
  { path: "admin/sights/edit/:id", component: EditSightComponent},



  // { path: "register", component: RegisterComponent},
];

@NgModule({
  imports: [
    HttpClientModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
