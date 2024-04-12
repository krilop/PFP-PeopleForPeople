import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class InterestsService {

  apiUrl = "http://localhost:8080/api/v1/PFP";
  constructor(private http:HttpClient) { }

  getInterests()
  {
    return this.http.get<any>(`${this.apiUrl}/interests/all`);
  }

  getInterestsForUser()
  {
    return this.http.get<any>(`${this.apiUrl}/interests/${localStorage.getItem('id')}/new`);
  }
  getInterestsOfUser(id:string)
  {
    return this.http.get<any>(`${this.apiUrl}/interests/${id}`);
  }
  deleteInterestOfUser(id:number)
  {
    return this.http.delete<any>(`${this.apiUrl}/interests/${localStorage.getItem('id')}/${id}`);
  }
  saveInterest(interest: any) {
    return this.http.post<any>(`${this.apiUrl}/interests/addNew`, interest);
  }
  addInterestForUser(id:number) {
    return this.http.post<any>(`${this.apiUrl}/interests/${localStorage.getItem('id')}/${id}`,'');
  }
}
