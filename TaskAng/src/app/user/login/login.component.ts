import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username!:string;
  password!:string;
  
 

  constructor(private service: AuthService, private http: HttpClient, private router: Router) {
  }

  ngOnInit(){  }

  login() {
    this.service.login(this.username,this.password).subscribe(data=>{
      console.log("Login data",data)
    })
  }
  }

