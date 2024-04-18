import {Component, Input} from '@angular/core';
import {UserInfoForListComponent} from "../user-info-for-list/user-info-for-list.component";
import {UserInfoDTO} from "../../DTO/UserInfoDTO";
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {UserService} from "../../services/user-info/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-change-user-info',
  standalone: true,
  imports: [
    UserInfoForListComponent,
    FormsModule,
    NgIf
  ],
  templateUrl: './change-user-info.component.html',
  styleUrl: './change-user-info.component.css'
})
export class ChangeUserInfoComponent {
  @Input() userInfo!: UserInfoDTO;

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

    // Проверяем, была ли дата рождения изменена
    if (this.user.dateOfBirth != null) {
      const dateObject = new Date(this.user.dateOfBirth);
      // Форматирование даты в нужный формат
      this.user.dateOfBirth = dateObject.toISOString().slice(0, 10);
    } else {
      // Если дата рождения не была изменена или уже в правильном формате, оставляем ее без изменений
      this.user.dateOfBirth = null;
    }

    this.userService.changeUser(this.user).subscribe(
      (response) => {
        console.log('User saved successfully:', response);
        this.router.navigateByUrl(`/profile/${localStorage.getItem('id')}`);
      },
      (error) => {
        console.error('Error saving user:', error);
      }
    );
  }


  previewImage() {
    this.user.media = this.user.imageLink;
  }
}
