import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SavingsComponent } from './user/savings/savings.component';
import { DashboardComponent } from './user/dashboard/dashboard.component';
import { CartComponent } from './shared-cart/cart/cart.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'savings', component: SavingsComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'cart',
    loadComponent: () =>
      import('./shared-cart/cart/cart.component').then(
        (m) => m.CartComponent
      ),
  }, 
];
