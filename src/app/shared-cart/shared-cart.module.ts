import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartComponent } from './cart/cart.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [CommonModule, CartComponent], // Import CartComponent instead of declaring it
})
export class SharedCartModule {}
