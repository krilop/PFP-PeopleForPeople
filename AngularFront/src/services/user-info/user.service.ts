import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  apiUrl = "http://localhost:8080/api/v1/PFP";
  constructor(private http:HttpClient) { }

  getUsers()
  {
    return this.http.get<any>(`${this.apiUrl}/userlist`);
  }
}
