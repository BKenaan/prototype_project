import { Component, OnInit } from '@angular/core';
import { DashboardService } from './dashboard.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [DashboardService]
})
export class DashboardComponent implements OnInit {
  totalSavings = 0;
  totalExpenses = 0;
  pendingTasks = 0;
  totalCartValue = 0;
  pendingOrders = 0;
  recentActivities: { description: string; date: string; status: string }[] = [];

  constructor(private dashboardService: DashboardService) {}

  ngOnInit(): void {
    const cartId = 'cart1';
    const individualDeliveryFee = 10;
    const groupDeliveryFee = 5;

    this.dashboardService.getSavingsDetails(cartId, individualDeliveryFee, groupDeliveryFee).subscribe(
      (data) => {
        this.totalSavings = data.totalSavings;
        this.totalExpenses = data.totalExpenses || 0;
      },
      (error) => {
        console.error('Error fetching savings details:', error);
      }
    );

    this.dashboardService.getTotalCartValue(cartId).subscribe(
      (value) => {
        this.totalCartValue = value;
      },
      (error) => {
        console.error('Error fetching total cart value:', error);
      }
    );

    this.dashboardService.getPendingOrders().subscribe(
      (count) => {
        this.pendingOrders = count;
      },
      (error) => {
        console.error('Error fetching pending orders:', error);
      }
    );

    // Fetch recent activities
    this.dashboardService.getRecentActivities().subscribe(
      (activities) => {
        this.recentActivities = activities;
      },
      (error) => {
        console.error('Error fetching recent activities:', error);
      }
    );
  }
}
