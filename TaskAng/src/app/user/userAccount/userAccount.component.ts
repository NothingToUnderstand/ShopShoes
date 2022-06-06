import { Component, OnInit } from '@angular/core';


import { ActivatedRoute, Router } from '@angular/router';
import { UserServiceService } from '../service/userService.service';
import { User } from '../user';
@Component({
  selector: 'app-userAccount',
  templateUrl: './userAccount.component.html',
  styleUrls: ['./userAccount.component.css']
})


  export class UserAccountComponent implements OnInit {

  user=new User();
    constructor(private service:UserServiceService,private router:Router,private path:ActivatedRoute) { }
  
    ngOnInit() {
      this.userAccount();
    }
  userAccount(){
    console.log("account")
    this.service.account().subscribe((data)=>{
      console.log("User active data",data);
      this.user=data;
      console.log("User active",this.user);
    })
  }
  }




