import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-cart',
    standalone: true,
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cartItems = [
    { name: 'Milk', quantity: 2, addedBy: 'User A', price: 2.5 },
    { name: 'Eggs', quantity: 1, addedBy: 'User B', price: 3.0 }
  ];
  totalCost = 8.0;

  constructor() {}

  ngOnInit(): void {}

  removeFromCart(item: any): void {
    console.log(`Removing ${item.name} from cart`);
  }
}
