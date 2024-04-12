import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostUserInfoComponent } from './post-user-info.component';

describe('PostUserInfoComponent', () => {
  let component: PostUserInfoComponent;
  let fixture: ComponentFixture<PostUserInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostUserInfoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PostUserInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
