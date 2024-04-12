import { Component, Output, EventEmitter } from '@angular/core';
import {UserService} from "../../services/user-info/user.service";
import {FormsModule} from "@angular/forms";
import {CommonModule, NgIf} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post-user-info',
  standalone: true,
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './post-user-info.component.html',
  styleUrl: './post-user-info.component.css'
})
export class PostUserInfoComponent {

  user: any = {
    id:'',
    name: '',
    lastName: '',
    description: '',
    dateOfBirth: null,
    gender: '',
    media: ''
  };

  constructor(private userService:UserService, private router:Router) {
  }
  onSubmit() {
    if (this.user.gender === 'male') {
      this.user.gender = true;
    } else {
      this.user.gender = false;
    }
    this.user.id = localStorage.getItem('id');
    const dateObject = new Date(this.user.dateOfBirth);
    // Форматирование даты в нужный формат
    this.user.dateOfBirth = dateObject.toISOString().slice(0, 10);
    this.userService.saveUser(this.user).subscribe(
      (response) => {
        console.log('User saved successfully:', response);
        this.router.navigateByUrl('/profile');
      },
      (error) => {
        console.error('Error saving user:', error);
      });
  }
  previewImage() {
    this.user.media = this.user.imageLink;
  }
}
