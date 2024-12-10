import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CartItem } from './app/shared-cart/shared-cart.model';

@Injectable({
  providedIn: 'root',
})
export class HelpersService {
  private apiUrl = 'http://localhost:5400/api/helpers';

  constructor(private http: HttpClient) {}

  createSharedCart(hostId: string, participants: string[], deadline: string): Observable<string> {
    const requestBody = { hostId, participants, deadline };
    return this.http.post<string>(`${this.apiUrl}/create-cart`, requestBody);
  }

  addItemToCart(cartId: string, userId: string, item: CartItem): Observable<void> {
    const requestBody = { cartId, userId, item };
    return this.http.post<void>(`${this.apiUrl}/add-item`, requestBody);
  }  

  calculateTotalCost(cartId: string): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/calculate-total/${cartId}`);
  }
}
