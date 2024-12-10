import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BackendService {
  private apiUrl = 'http://localhost:8080/api'; // Replace with your backend URL

  constructor(private http: HttpClient) {}

  getSavingsSummary(): Observable<any> {
    return this.http.get(`${this.apiUrl}/savings-summary`);
  }

  saveUserData(userData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/save-user`, userData);
  }

  calculateSavings(data: { income: number; expenses: number }): Observable<any> {
    return this.http.post(`${this.apiUrl}/calculate-savings`, data);
  }
}
