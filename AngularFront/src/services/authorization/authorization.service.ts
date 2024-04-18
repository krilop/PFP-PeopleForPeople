import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import { JwtHelperService } from '@auth0/angular-jwt';
@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  apiUrl = "http://localhost:8080/auth";
  jwtHelper: JwtHelperService = new JwtHelperService(); // Создаем экземпляр сервиса для работы с JWT

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/sign-in`, { username, password })
      .pipe(
        tap(response => {
          // Сохраняем токен в локальное хранилище
          localStorage.setItem('jwtToken', response.token);

          // Извлекаем идентификатор пользователя из токена
          const userId: string = this.getUserIdFromToken(response.token);
          localStorage.setItem('id', userId);
        })
      );
  }

  // Функция для извлечения идентификатора пользователя из токена JWT
  getUserIdFromToken(token: string): string {
    const decodedToken = this.jwtHelper.decodeToken(token);
    return decodedToken.id;
  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/sign-up`, { username, email, password })
      .pipe(
        tap(response => {
          // Сохраняем токен в локальное хранилище
          localStorage.setItem('jwtToken', response.token);
          // Извлекаем идентификатор пользователя из токена
          const userId: string = this.getUserIdFromToken(response.token);
          localStorage.setItem('id', userId);
        })
      );
  }

}
