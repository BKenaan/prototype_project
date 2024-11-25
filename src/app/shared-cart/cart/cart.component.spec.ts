import { TestBed } from '@angular/core/testing';
import { CartComponent } from './cart.component';

describe('CartComponent', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [CartComponent], // Import CartComponent as it's standalone
    }).compileComponents();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(CartComponent);
    const component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });
});
