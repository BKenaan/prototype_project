import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-cart',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cartItems = [
    { name: 'Product A', quantity: 1, price: 100 },
    { name: 'Product B', quantity: 2, price: 150 },
    { name: 'Product C', quantity: 3, price: 50 },
  ];
  get totalPrice() {
    return this.cartItems.reduce((total, item) => total + item.quantity * item.price, 0);
  }
  updateQuantity(index: number, newQuantity: number){
    if (newQuantity >= 0){
      this.cartItems[index].quantity = newQuantity;
    }
  }
  removeItem(index: number){
    this.cartItems.splice(index, 1);
  }

  clearCart(){
    this.cartItems = [];
  }

  constructor() {}

  ngOnInit(): void {}

  removeFromCart(item: any): void {
    console.log(`Removing ${item.name} from cart`);
  }
}
