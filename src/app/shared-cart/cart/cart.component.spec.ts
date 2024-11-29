import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CartComponent } from './cart.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OpenFoodApiService } from '../openfood-api.service';
import { of } from 'rxjs';

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;
  let openFoodApiService: jasmine.SpyObj<OpenFoodApiService>;

  beforeEach(async () => {
    const openFoodApiSpy = jasmine.createSpyObj('OpenFoodApiService', ['searchProducts']);

    await TestBed.configureTestingModule({
      declarations: [CartComponent],
      imports: [FormsModule, CommonModule],
      providers: [{ provide: OpenFoodApiService, useValue: openFoodApiSpy }],
    }).compileComponents();

    openFoodApiService = TestBed.inject(OpenFoodApiService) as jasmine.SpyObj<OpenFoodApiService>;
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
