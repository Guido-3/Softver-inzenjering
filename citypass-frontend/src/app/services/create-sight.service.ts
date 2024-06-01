import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateSightService {

  private apiUrl = 'http://localhost:8080/citypass-api/znamenitost'; 

  constructor(private http: HttpClient) { }
  
  createSight(sight: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.apiUrl, sight, { headers });
  }
}
