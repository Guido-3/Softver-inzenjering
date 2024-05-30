import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DailyService {

  constructor(private http: HttpClient) { }
  
  getDailyPass(){
    return this.http.get('http://localhost:8080/citypass-api/daily-pass'); 
  }
}