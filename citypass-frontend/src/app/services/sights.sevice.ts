import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SightsService {

  constructor(private http: HttpClient) { }
  
  getSights(){
    return this.http.get('http://localhost:8080/citypass-api/znamenitost'); 
  }
}