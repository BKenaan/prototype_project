import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {
  totalSavings = 1500;
  totalExpenses = 400;
  pendingTasks = 3;
  totalCartValue = 0;
  pendingOrders = 0;
}
