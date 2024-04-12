import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {AuthorizationService} from "../../services/authorization/authorization.service";
import {Router} from "@angular/router";
import {CommonModule} from "@angular/common";


@Component({
  selector: 'app-authorization',
  standalone: true,
  imports: [
    FormsModule,CommonModule
  ],
  templateUrl:'authorization.component.html',
  styleUrl: 'authorization.component.css'
})
export class AuthorizationComponent {
  username: string = '';
  password: string = '';
  errorMessage: any;

  constructor(
    private router: Router,
    private authorizationService: AuthorizationService
  ) {}

  login() {
    this.authorizationService.login(this.username, this.password)
      .subscribe(
        response => {
          // Переадресация на страницу /userlist после успешной аутентификации
          this.router.navigate(['/userlist']);
        },
        error => {
          // Сохраняем сообщение об ошибке
          alert('Ошибка аутентификации. Проверьте введенные данные.')
          console.error('Login error', error);
        }
      );
  }
}
