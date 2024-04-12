import {
  HttpResponse, HttpErrorResponse, HttpInterceptorFn
} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import * as jwt_decode from 'jwt-decode';

export const JwtInterceptor: HttpInterceptorFn = (req, next) => {
  // Получаем токен из локального хранилища
  console.log('Intercepted request:', req);
  const jwtToken = localStorage.getItem('jwtToken');

  if (jwtToken) {
    console.log(jwtToken);
    if (!localStorage.getItem('id')) {
      const decodedToken: any = jwt_decode.jwtDecode(jwtToken);
      localStorage.setItem('id', decodedToken.id);
      console.log(localStorage.getItem('id'));
    }

    // Добавляем токен в заголовок Authorization
    req = req.clone({
      setHeaders: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}`
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
