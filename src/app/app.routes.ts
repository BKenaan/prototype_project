import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SavingsComponent } from './user/savings/savings.component';
import { CartComponent } from './shared-cart/cart/cart.component';
import { DashboardComponent } from './user/dashboard/dashboard.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'savings', component: SavingsComponent },
  { path: 'cart', component: CartComponent },
  { path: 'dashboard', component: DashboardComponent},
];
