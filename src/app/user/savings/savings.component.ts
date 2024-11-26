import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-savings',
  standalone: true,
  imports: [CommonModule], // Add CommonModule here
  templateUrl: './savings.component.html',
  styleUrls: ['./savings.component.css'],
})
export class SavingsComponent {
  savingsData = [
    { category: 'Food', amount: 200 },
    { category: 'Delivery', amount: 500 },
  ];
}
