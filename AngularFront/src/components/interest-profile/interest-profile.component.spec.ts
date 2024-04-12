import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterestProfileComponent } from './interest-profile.component';

describe('InterestProfileComponent', () => {
  let component: InterestProfileComponent;
  let fixture: ComponentFixture<InterestProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InterestProfileComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InterestProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
