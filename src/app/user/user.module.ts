import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SavingsComponent } from './savings/savings.component';

@NgModule({
  declarations: [SavingsComponent],
  imports: [CommonModule],
  exports: [SavingsComponent],
})
export class UserModule {}
