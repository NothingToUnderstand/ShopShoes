import { Injectable } from '@angular/core';
import{ HttpClient}from '@angular/common/http';
import { Observable } from 'rxjs';
import { Shoes } from '../shoes';
import { ResponseShoes } from '../ResponseShoes';


@Injectable({
  providedIn: 'root'
})
export class ShoesServiceService {

 private baseUrl='http://localhost:8080/rest/shoes';
 shoes!:Shoes[];

constructor( private http:HttpClient) { }


getShoesListData():Observable<ResponseShoes>{
 return this.http.get<ResponseShoes>(this.baseUrl);
}
deleteShoes(id:number):Observable<Shoes[]>{
  return this.http.delete<Shoes[]>(`${this.baseUrl}/${id}`);
}
createShoes(shoes:Shoes):Observable<Object>{
return this.http.post(`${this.baseUrl}`,shoes);
}
infoShoes(id:number):Observable<Shoes>{
  return this.http.get<Shoes>(`${this.baseUrl}/${id}`);
}
editShoes(id:number,shoes:Shoes):Observable<Object>{
  return this.http.post(`${this.baseUrl}/${id}`,shoes);
}
searchShoes(name:string):Observable<Shoes[]>{
  return this.http.get<Shoes[]>(`${this.baseUrl}?name=${name}`);
}
sort(pageNo:number,pageSize:number,sortField:string,sortDir:string):Observable<Shoes[]>{
  return this.http.get<Shoes[]>(`${this.baseUrl}/page/${pageNo}/size/${pageSize}?sortField=${sortField}&sortDir=${sortDir}`);
}

}