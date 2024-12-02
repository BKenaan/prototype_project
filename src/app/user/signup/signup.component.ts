import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  imports: [FormsModule, RouterModule]
})
export class SignupComponent {
  username: string = '';
  email: string = '';
  password: string = '';

  onSignup() {
    console.log('User signed up:', { username: this.username, email: this.email, password: this.password });
    // Add service call for backend API to register user
  }
}
