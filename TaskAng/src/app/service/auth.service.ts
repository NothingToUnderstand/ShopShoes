import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root',
  })
  export class AuthService{
    loginUrl="http://localhost:8080/login"
    constructor( private http:HttpClient) { }
    public login(username:string,password:string){
    
        return this.http.post(`${this.loginUrl}`,{
         
           username: username,
            password:password
        
        });
        }
        
        
        public isAuthenticated(): boolean {
        return false; 
        }
        
        public logout(): void {
        // ...
        }
  }
  