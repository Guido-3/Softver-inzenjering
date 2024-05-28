import { Component } from '@angular/core';

// dekorater - klasu pretvara u komponentu
// selector - ime pod kojim komponentu koristimo u html body
// templateUrl - putanja do html fajla u kom je definisan sadrzaj ove komponente
// styleUrls - putanja do scss fajla koji se primjenjuje nad ovom komponentom
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'citypass-frontend';
}
