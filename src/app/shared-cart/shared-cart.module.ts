import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartComponent } from './cart/cart.component'; // Ensure this is a standalone component

@NgModule({
  imports: [CommonModule, CartComponent], // Import CartComponent instead of declaring it
})
export class SharedCartModule {}
