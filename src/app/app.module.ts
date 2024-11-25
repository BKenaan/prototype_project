import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { UserModule } from './user/user.module'; // Import other feature modules if needed
import { SharedCartModule } from './shared-cart/shared-cart.module'; // Import shared-cart module
import { provideRouter } from '@angular/router';
import { routes } from './app.routes'; // Import the routes configuration

@NgModule({
  imports: [
    BrowserModule,
    UserModule, // Feature module
    SharedCartModule, // Feature module
  ],
  providers: [
    provideRouter(routes), // Provide the routes
  ],
})
export class AppModule {}
