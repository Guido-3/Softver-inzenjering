import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SightsService {

  private baseUrl = 'http://localhost:8080/citypass-api/znamenitost'; // URL vašeg backenda

  constructor(private http: HttpClient) { }

  getSights(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}`);
  }
  
}