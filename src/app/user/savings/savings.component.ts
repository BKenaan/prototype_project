import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-savings',
  templateUrl: './savings.component.html',
  styleUrls: ['./savings.component.css']
})
export class SavingsComponent implements OnInit {
  individualDeliveryFee = 5.0;
  groupDeliveryFee = 8.0;
  participants = 4;

  get savings(): number {
    return (this.individualDeliveryFee * this.participants) - this.groupDeliveryFee;
  }

  constructor() {}

  ngOnInit(): void {}
}
