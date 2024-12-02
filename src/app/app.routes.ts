import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SavingsComponent } from './user/savings/savings.component';
import { DashboardComponent } from './user/dashboard/dashboard.component';
import { CartComponent } from './shared-cart/cart/cart.component';
import { SignupComponent } from './user/signup/signup.component';
import { LoginComponent } from './user/login/login.component';

export const routes: Routes = [
  { path: '', redirectTo: '/signup', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'savings', component: SavingsComponent },
  { 
    path: 'dashboard', 
    loadComponent: () =>
      import('./user/dashboard/dashboard.component').then(
        (m) => m.DashboardComponent
      ),
  },
  { path: 'cart',
    loadComponent: () =>
      import('./shared-cart/cart/cart.component').then(
        (m) => m.CartComponent
      ),
  },
  {path: 'signup', component: SignupComponent},
  {path: 'login', component:LoginComponent} 
];
