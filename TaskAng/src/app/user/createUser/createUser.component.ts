import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserServiceService } from '../service/userService.service';
import { User } from '../user';

@Component({
  selector: 'app-createUser',
  templateUrl: './createUser.component.html',
  styleUrls: ['./createUser.component.css']
})
export class CreateUserComponent implements OnInit {

  user=new User();
  constructor(private service:UserServiceService,private router:Router,private path:ActivatedRoute) { }

  ngOnInit() {
      
  }
  goToList(){
        this.router.navigate(['/users']);
    }
    onSubmit(){
  console.log(this.user);
  this.saveUser();
  
    }
     saveUser(){
      this.service.createUser(this.user).subscribe((data)=>{
        console.log("Created:",data);
        this.goToList();
      })
  }
}