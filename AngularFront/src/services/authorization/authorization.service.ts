import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  apiUrl = "http://localhost:8080/auth";
  constructor(private http:HttpClient) { }


  // В вашем сервисе аутентификации
  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/sign-in`, { username, password })
      .pipe(
        tap(response => {
          // Сохраняем токен в локальное хранилище
          localStorage.setItem('jwtToken', response.token);
        })
      );
  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/sign-up`, { username, email, password })
      .pipe(
        tap(response => {
          // Сохраняем токен в локальное хранилище
          localStorage.setItem('jwtToken', response.token);
        })
      );
  }

}
