import { TestBed } from '@angular/core/testing';
import { SavingsComponent } from './savings.component';

describe('SavingsComponent', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [SavingsComponent], // Import SavingsComponent as it's standalone
    }).compileComponents();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(SavingsComponent);
    const component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });
});
