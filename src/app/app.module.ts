import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { routes } from './app.routes';
import { HomeComponent } from './home/home.component';
import { SavingsComponent } from './user/savings/savings.component';
import { DashboardComponent } from './user/dashboard/dashboard.component';
import { SharedCartModule } from './shared-cart/shared-cart.module'; // Import SharedCartModule

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SavingsComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    SharedCartModule, // Include the SharedCartModule
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
