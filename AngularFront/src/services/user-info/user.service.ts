import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserInfoDTO} from "../../DTO/UserInfoDTO";

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

  getUser(id:any)
  {
    return this.http.get<any>(`${this.apiUrl}/profile/${id}/info`);
  }
  saveUser(user: any) {
    return this.http.post<any>(`${this.apiUrl}/profile/${localStorage.getItem('id')}/change`, user);
  }
}
