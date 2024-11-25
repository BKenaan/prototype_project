import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SavingsComponent } from './savings.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

describe('SavingsComponent', () => {
  let component: SavingsComponent;
  let fixture: ComponentFixture<SavingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SavingsComponent], // Declare the component
      schemas: [CUSTOM_ELEMENTS_SCHEMA], // Ignore unknown elements if any
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SavingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
