import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router'; // Import RouterModule
import { AppComponent } from './app.component';
import { routes } from './app.routes'; // Import your routes
import { HomeComponent } from './home/home.component';
import { SavingsComponent } from './user/savings/savings.component';
import { CartComponent } from './shared-cart/cart/cart.component';
import { DashboardComponent } from './user/dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SavingsComponent,
    CartComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes), // Configure RouterModule with your routes
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
