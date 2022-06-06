import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserServiceService } from '../service/userService.service';
import { User } from '../user';


@Component({
  selector: 'app-userInfo',
  templateUrl: './userInfo.component.html',
  styleUrls: ['./userInfo.component.css']
})
export class UserInfoComponent implements OnInit {
  id!:number;
  user=new User();
    constructor(private service:UserServiceService,private router:Router,private path:ActivatedRoute) { }
  
    ngOnInit() {
      this.infoUser();
    }
  infoUser(){
    this.id=this.path.snapshot.params['id'];
    this.service.infoUser(this.id).subscribe((data)=>{
    this.user=data;
      console.log("Find shoes",this.user);
    })
  }
  }