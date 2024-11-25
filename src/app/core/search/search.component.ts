import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-search',
  standalone: true,
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  searchQuery: string = '';
  products: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {}

  searchProducts(): void {
    this.http.get(`https://api.example.com/products?query=${this.searchQuery}`)
      .subscribe((data: any) => {
        this.products = data;
      });
  }

  addToCart(product: any): void {
    // Add product to cart logic
    console.log(`Adding ${product.name} to cart`);
  }
}
