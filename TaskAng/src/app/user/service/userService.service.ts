import { Injectable } from '@angular/core';
import{ HttpClient}from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../user';


@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

 private baseUrl='http://localhost:8080/rest/users';
 user!:User[];

constructor( private http:HttpClient) { }


getUserListData():Observable<User[]>{
 return this.http.get<User[]>(this.baseUrl);
}
deleteUser(id:number):Observable<User[]>{
  return this.http.delete<User[]>(`${this.baseUrl}/${id}`);
}
createUser(user:User):Observable<Object>{
return this.http.post(`${this.baseUrl}`,user);
}
infoUser(id:number):Observable<User>{
  return this.http.get<User>(`${this.baseUrl}/${id}`);
}
editUser(id:number,user:User):Observable<Object>{
  return this.http.post(`${this.baseUrl}/${id}`,user);
}
searchUser(username:string):Observable<User[]>{
  return this.http.get<User[]>(`${this.baseUrl}?username=${username}`);
}
sort(pageNo:number,pageSize:number,sortField:string,sortDir:string):Observable<User[]>{
  return this.http.get<User[]>(`${this.baseUrl}/page/${pageNo}/size/${pageSize}?sortField=${sortField}&sortDir=${sortDir}`);
}
account():Observable<User>{
  return this.http.get<User>(`${this.baseUrl}/acc`);
}

}
