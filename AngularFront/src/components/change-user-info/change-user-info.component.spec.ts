import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeUserInfoComponent } from './change-user-info.component';

describe('ChangeUserInfoComponent', () => {
  let component: ChangeUserInfoComponent;
  let fixture: ComponentFixture<ChangeUserInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChangeUserInfoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChangeUserInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
