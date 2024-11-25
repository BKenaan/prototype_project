import { Routes } from '@angular/router';
import { SavingsComponent } from './user/savings/savings.component';
import { CartComponent } from './shared-cart/cart/cart.component';

export const routes: Routes = [
  { path: '', redirectTo: 'savings', pathMatch: 'full' }, // Default route
  { path: 'savings', component: SavingsComponent },      // Route for SavingsComponent
  { path: 'cart', component: CartComponent },            // Route for CartComponent
];