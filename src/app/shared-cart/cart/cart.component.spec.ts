import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CartComponent } from './cart.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CartComponent],
      imports: [FormsModule, CommonModule], // Import necessary modules
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should remove an item from the cart', () => {
    component.cartItems = [
      { name: 'Milk', quantity: 2, price: 1.5 },
      { name: 'Bread', quantity: 1, price: 0.8 },
    ];
    component.removeItem(0);
    expect(component.cartItems.length).toBe(1);
    expect(component.cartItems[0].name).toBe('Bread');
  });
});
