import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Status, Task, Type} from './task.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = 'http://localhost:8080/users';
  constructor(private http: HttpClient) { }
  // a
  createUser(name: string , lastName: string , email: string , password: string ): Observable<User> {
    return this.http.post<User>(`${this.url}?name=${name}&lastName=${lastName}&email=${email}&password=${password}`
      , null);
  }
// b
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.url);
  }
// c endpoint z api rozbity na 2 metody
  getByEmailOr(email: string): Observable<User[]>{
    return this.http.get<User[]>(`${this.url}/IdOrEmail?email=${email}`);
  }
  getByIdOr(userId: number): Observable<User>{
    return this.http.get<User>(`${this.url}/IdOrEmail?userId=${userId}`);
  }
  // d
  activateUser(userId: number ): Observable<User>{
    return  this.http.put<User>(`${this.url}/Activate?userId=${userId}`, null);
  }
  // e
  deleteUserById(userId: number): Observable<{}>{
    return this.http.delete(`${this.url}/?userId=${userId}`);
  }

}
export interface User {
  userId: number;
  name: string;
  lastName: string;
  email: string;
  password: string;
  status: boolean;
  registrationDateTime: Date;
}
