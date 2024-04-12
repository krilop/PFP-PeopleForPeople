import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {AuthorizationService} from "../../services/authorization/authorization.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {
  username: string = '';
  email: string = '';
  password: string = '';


  constructor(private authorizationService:AuthorizationService, private router: Router) {
  }
  register() {
    this.authorizationService.register(this.username, this.email, this.password)
      .subscribe(
        response => {
          // Если регистрация прошла успешно и вернулся токен
          if (response.token) {
            // Сохраняем токен в локальное хранилище
            localStorage.setItem('jwtToken', response.token);
            // Перенаправляем пользователя на другую страницу
            this.router.navigate(['/registration/part2']); // Указывайте путь к нужной странице
          }
        },
        error => {
          // Обработка ошибок регистрации, например, вывод сообщения об ошибке
          console.error('Registration error', error);
        }
      );
  }
}
