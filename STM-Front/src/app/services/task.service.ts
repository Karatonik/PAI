import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from './user.service';
@Injectable({
  providedIn: 'root'
})
export class TaskService {
  url = 'http://localhost:8080/tasks';
  constructor(private http: HttpClient) { }
  // f
  createTask(title: string , description: string , type: Type , status: Status , userId: number ): Observable<Task> {
    return this.http.post<Task>(`${this.url}?title=${title}&description=${description}&type=${type}&status=${status}&user_id=${userId}`
      , null);
  }
// g
  getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.url);
  }
// h endpoint z api rozbity na 3 metody
  getByTitleOr(title: string ): Observable<Task[]>{
    return this.http.get<Task[]>(`${this.url}/TitleOrStatusOrType?title=${title}`);
  }
  getByStatusOr(status: Status): Observable<Task[]>{
    return this.http.get<Task[]>(`${this.url}/TitleOrStatusOrType?status=${status}`);
  }
  getByTypeOr(type: Type): Observable<Task[]>{
    return this.http.get<Task[]>(`${this.url}/TitleOrStatusOrType?type=${type}`);
  }
  // i
  changeTaskStatus(taskId: number , status: Status): Observable<Task>{
    return  this.http.put<Task>(`${this.url}/ChangeStatus?taskId=${taskId}&status=${status}`, null);
  }
  // j
  deleteTaskById(taskId: number): Observable<{}>{
    return this.http.delete(`${this.url}/?taskId=${taskId}`);
  }

}
export enum Type {
  TASK= 'TASK',
  BUG= 'BUG',
  FEATURE= 'FEATURE'
}
export enum Status {
  NEW= 'NEW',
  IN_PROGRESS= 'IN_PROGRESS',
  DONE= 'DONE'
}


export interface Task {
    taskId: number;
    title: string;
    description: string;
    dateAdded: Date;
    type: Type;
    status: Status;
    user: User;
  }
