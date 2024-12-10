import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OpenFoodApiService } from '../openfood-api.service';
import { HelpersService } from '../../../helpers.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  cart: any = null;
  cartItems: { name: string; quantity: number; price?: number }[] = [];
  cartIdInput: string = '';
  pinInput: string = '';
  participantId: string = '';
  searchQuery: string = '';
  searchResults: any[] = [];
  totalCost: number = 0;

  participants: string[] = []; // Explicitly define the type as string[]

  constructor(
    private openFoodApi: OpenFoodApiService,
    private helpersService: HelpersService
  ) {}

  removeItem(index: number): void {
    this.cartItems.splice(index, 1);
  }

  createCart(hostId: string): void {
    const deadline = new Date().toISOString(); // Replace with actual deadline
    this.helpersService.createSharedCart(hostId, this.participants, deadline).subscribe((cartId) => {
      this.cart = {
        cartId: cartId,
        hostId,
        participants: this.participants,
        items: [],
      };
      console.log('Cart created with ID:', cartId);
    });
  }

  joinCart(cartId: string, pin: string): void {
    if (this.cart?.cartId === cartId && this.cart.pin === pin) {
      if (!this.cart.participants.includes(this.participantId)) {
        this.cart.participants.push(this.participantId);
      }
    }
  }

  searchProducts(): void {
    this.openFoodApi.searchProducts(this.searchQuery).subscribe((response) => {
      this.searchResults = response.products || [];
    });
  }

  addToCart(product: any): void {
    if (!this.cart) return;
    const item = {
      productId: product.id || product.code,
      name: product.product_name,
      addedBy: this.participantId,
      quantity: 1,
      price: product.nutriments?.energy_value || 0,
    };
    this.helpersService.addItemToCart(this.cart.cartId, this.participantId, item).subscribe(() => {
      this.cart.items.push(item);
      console.log('Item added to cart:', item);
    });
  }

  calculateTotalCost(): void {
    if (!this.cart) return;
    this.helpersService.calculateTotalCost(this.cart.cartId).subscribe((total) => {
      this.totalCost = total;
      console.log('Total cost:', total);
    });
  }
}
