import { Routes } from '@angular/router';
import { SavingsComponent } from './user/savings/savings.component';
import { CartComponent } from './shared-cart/cart/cart.component';

export const routes: Routes = [
  { path: '', redirectTo: 'savings', pathMatch: 'full' },
  { path: 'savings', component: SavingsComponent },
  { path: 'cart', component: CartComponent },
];
