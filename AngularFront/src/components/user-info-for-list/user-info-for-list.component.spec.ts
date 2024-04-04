import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserInfoForListComponent } from './user-info-for-list.component';

describe('UserInfoForListComponent', () => {
  let component: UserInfoForListComponent;
  let fixture: ComponentFixture<UserInfoForListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserInfoForListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UserInfoForListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
