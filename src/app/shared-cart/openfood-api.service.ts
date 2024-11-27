import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root', // This ensures it’s globally available
})
export class OpenFoodApiService {
  private apiUrl = 'https://world.openfoodfacts.org';

  constructor(private http: HttpClient) {}

  searchProducts(query: string): Observable<any> {
    const url = `${this.apiUrl}/cgi/search.pl?search_terms=${query}&json=true`;
    return this.http.get(url);
  }
}
