import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // For [(ngModel)]
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [FormsModule, CommonModule],
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  message: string = '';

  constructor(private router: Router) {}

  login() {
    // Dummy login credentials
    const validUsername = 'test';
    const validPassword = 'test';

    if (this.username === validUsername && this.password === validPassword) {
      this.message = 'Login successful!';
      // Redirect to dashboard or home page
      this.router.navigate(['/dashboard']);
    } else {
      this.message = 'Invalid credentials. Please try again.';
    }
  }
}
