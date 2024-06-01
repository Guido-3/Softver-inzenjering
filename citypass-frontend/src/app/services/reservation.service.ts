import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private baseUrl = 'http://localhost:8080/citypass-api/turista_daily_pass'; // URL vašeg backenda

  constructor(private http: HttpClient) { }

  getSights(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}`);
  }
}