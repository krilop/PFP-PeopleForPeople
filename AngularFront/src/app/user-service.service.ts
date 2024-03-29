import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Authorization } from "../model/authorization";
import {Observable} from "rxjs";


@Injectable()
export class UserService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users';
  }

  public findAll(): Observable<Authorization[]> {
    return this.http.get<Authorization[]>(this.usersUrl);
  }

  public save(user: Authorization) {
    return this.http.post<Authorization>(this.usersUrl, user);
  }
}
