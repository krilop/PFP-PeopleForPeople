import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomeInformationComponent } from './welcome-information.component';

describe('WelcomeInformationComponent', () => {
  let component: WelcomeInformationComponent;
  let fixture: ComponentFixture<WelcomeInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WelcomeInformationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WelcomeInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
