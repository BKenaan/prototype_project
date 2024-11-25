import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SharedCartModule } from '../../shared-cart/shared-cart.module';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing'; // For HTTP service testing
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // If using forms
import { CartComponent } from '../../shared-cart/cart/cart.component';

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CartComponent], // Declare the component here
      imports: [
        HttpClientTestingModule, // Add this if your component makes API calls
        FormsModule,
        ReactiveFormsModule,
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA], // Ignore unrecognized elements (e.g., Material components)
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
