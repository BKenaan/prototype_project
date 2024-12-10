import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { HelpersService } from '../../../helpers.service';

@Component({
  selector: 'app-savings',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './savings.component.html',
  styleUrls: ['./savings.component.css'],
  providers: [HelpersService]
})
export class SavingsComponent {
  savingsData: { category: string; amount: number }[] = [];

  constructor(private http: HttpClient) {
    this.http.get<{ category: string; amount: number }[]>('/api/helpers/view-cart/cart1').subscribe({
      next: (response) => {
        this.savingsData = response;
      },
      error: (error) => console.error('Error fetching savings data:', error),
    });
  }
}