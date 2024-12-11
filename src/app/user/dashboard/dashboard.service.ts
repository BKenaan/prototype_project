import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private apiUrl = 'http://localhost:5400/api/dashboard';

  constructor(private http: HttpClient) {}

  getSavingsDetails(cartId: string, individualDeliveryFee: number, groupDeliveryFee: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/savings-details/${cartId}`, {
      params: { individualDeliveryFee: individualDeliveryFee.toString(), groupDeliveryFee: groupDeliveryFee.toString() },
    });
  }

  getTotalCartValue(cartId: string): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/total-cart-value/${cartId}`);
  }

  getPendingOrders(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/pending-orders`);
  }

  getRecentActivities(): Observable<{ description: string; date: string; status: string }[]> {
    return this.http.get<{ description: string; date: string; status: string }[]>(`${this.apiUrl}/recent-activities`);
  }
}
