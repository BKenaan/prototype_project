import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SharedCartModule } from '../shared-cart.module'; // Import the module
import { CartComponent } from './cart.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing'; // For HTTP testing
import { RouterTestingModule } from '@angular/router/testing'; // If Router is used

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        SharedCartModule, // Import the module containing CartComponent
        HttpClientTestingModule,
        RouterTestingModule,
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA], // Ignore custom elements if any
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
});
