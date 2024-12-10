import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { CartItem } from './app/shared-cart/shared-cart.model';

@Injectable({
  providedIn: 'root',
})
export class HelpersService {
  private apiUrl = 'http://localhost:5400/api/helpers';

  constructor(private http: HttpClient) {}

  createSharedCart(hostId: string, participants: string[], deadline: string): Observable<string> {
    const requestBody = { hostId, participants, deadline };
    return this.http.post<string>('http://localhost:5400/api/helpers/create-cart', requestBody).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error details:', error);
        return throwError(() => new Error('Something went wrong!'));
      })
    );
  }
  

  addItemToCart(cartId: string, userId: string, item: CartItem): Observable<void> {
    const requestBody = { cartId, userId, item };
    return this.http.post<void>(`${this.apiUrl}/add-item`, requestBody);
  }  

  calculateTotalCost(cartId: string): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/calculate-total/${cartId}`);
  }
}
