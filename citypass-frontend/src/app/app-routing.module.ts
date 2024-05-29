import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/presentation/home/home.component';
import { ContactComponent } from './components/presentation/contact/contact.component';
import { DailyPassesComponent } from './components/presentation/daily-passes/daily-passes.component';

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "about", component: ContactComponent },
  { path: "daily-passes", component: DailyPassesComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
