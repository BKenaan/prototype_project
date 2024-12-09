import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { routes } from './app.routes';
import { HomeComponent } from './home/home.component';
import { SavingsComponent } from './user/savings/savings.component';
import { DashboardComponent } from './user/dashboard/dashboard.component';
import { SharedCartModule } from './shared-cart/shared-cart.module';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './user/login/login.component';


@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    SharedCartModule,
  ],
  bootstrap: [],
})
export class AppModule {}
