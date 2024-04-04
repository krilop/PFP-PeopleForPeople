import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpResponse, HttpErrorResponse, HttpInterceptorFn
} from '@angular/common/http';
import {Observable, tap} from 'rxjs';

export const JwtInterceptor : HttpInterceptorFn = (req, next) => {
    // Получаем токен из локального хранилища
    console.log('Intercepted request:', req);
    const jwtToken = localStorage.getItem('jwtToken');

    if (jwtToken) {
      console.log(jwtToken);
      // Добавляем токен в заголовок Authorization
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${jwtToken}`
        }
      });
    }

    return next(req).pipe(
      tap(
        (event) => {
          if (event instanceof HttpResponse)
            console.log('Server response');
        },
        (err) => {
          if (err instanceof HttpErrorResponse) {
            if (err.status == 401)
              console.log('Unauthorized');
          }
        }
      )
    );
}
