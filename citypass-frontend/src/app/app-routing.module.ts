import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/presentation/home/home.component';
import { ContactComponent } from './components/presentation/contact/contact.component';
import { DailyPassesComponent } from './components/presentation/daily-passes/daily-passes.component';
import { BuyComponent } from './components/presentation/buy/buy.component';
import { LoginComponent } from './components/auth/login/login.component';
// import { RegisterComponent } from './components/auth/register/register.component';

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "about", component: ContactComponent },
  { path: "daily-passes", component: DailyPassesComponent},
  { path: "buy", component: BuyComponent},
  { path: "admin", component: LoginComponent},
  // { path: "register", component: RegisterComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
