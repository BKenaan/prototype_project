import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {
  activeOrders = [
    { id: 1, total: 120.0 },
    { id: 2, total: 200.5 },
  ];
  totalSavings = 50.5;
}
