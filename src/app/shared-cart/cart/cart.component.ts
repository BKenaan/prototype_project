import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OpenFoodApiService } from '../openfood-api.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, FormsModule], // Add necessary modules here
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  cart: any = null;
  cartIdInput: string = '';
  pinInput: string = '';
  participantId: string = '';
  searchQuery: string = '';
  searchResults: any[] = [];
  cartItems: { name: string; quantity: number }[] = [];
  

  constructor(private openFoodApi: OpenFoodApiService) {}

  removeItem(index: number): void {
        this.cartItems.splice(index, 1);
  }

  createCart(hostId: string) {
    this.cart = {
      cartId: Math.random().toString(36).substr(2, 9),
      hostId,
      pin: Math.floor(1000 + Math.random() * 9000).toString(),
      participants: [],
      items: [],
    };
  }

  joinCart(cartId: string, pin: string) {
    if (this.cart?.cartId === cartId && this.cart.pin === pin) {
      if (!this.cart.participants.includes(this.participantId)) {
        this.cart.participants.push(this.participantId);
      }
    }
  }

  searchProducts() {
    this.openFoodApi.searchProducts(this.searchQuery).subscribe((response) => {
      this.searchResults = response.products || [];
    });
  }

  addToCart(product: any) {
    if (!this.cart) return;
    const item = {
      productId: product.id || product.code,
      name: product.product_name,
      addedBy: this.participantId,
      quantity: 1,
      price: product.nutriments?.energy_value || 0,
    };
    this.cart.items.push(item);
  }
}
