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
  createZnamenitost(znamenitost: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}`, znamenitost);
  }
  getZnamenitost(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  updateZnamenitost(id: string, data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, data);
  }

}