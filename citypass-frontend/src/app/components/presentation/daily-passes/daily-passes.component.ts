import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Component({
  selector: 'app-daily-passes',
  templateUrl: './daily-passes.component.html',
  styleUrls: ['./daily-passes.component.scss']
})

@Injectable({
  providedIn: 'root'
})
export class DailyPassesComponent implements OnInit {
  znamenitosti: Znamenitost[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchAttractions();
  }

  fetchAttractions(){
    return this.http.get<Znamenitost[]>('http://localhost:8080/citypass-api/znamenitost').subscribe(
      data => {
        this.znamenitosti = data;
        console.log('Data fetched:', this.znamenitosti);
      },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }
}

interface Znamenitost {
  id: number;
  ime: string;
  opis: string;
  slika: string;
  admin: string;
}
