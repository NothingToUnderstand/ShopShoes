import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserServiceService } from '../service/userService.service';
import { User } from '../user';


@Component({
  selector: 'app-editUser',
  templateUrl: './editUser.component.html',
  styleUrls: ['./editUser.component.css']
})
export class EditUserComponent implements OnInit {
  id!:number;
  user=new User();
  constructor(private service:UserServiceService,private router:Router,private path:ActivatedRoute) { }

  ngOnInit() {
    this.infoUser();
  }
  goToList(){
    this.router.navigate(['/users']);
  }
editUser(){
  this.id=this.path.snapshot.params['id'];
  this.service.infoUser(this.id).subscribe((data)=>{
    this.user=data;
      console.log("Find user",this.user);
    })
  this.service.editUser(this.id,this.user).subscribe((data)=>{
    console.log("Edited:",data);
    this.goToList();
  })
}
infoUser(){
  this.id=this.path.snapshot.params['id'];
  this.service.infoUser(this.id).subscribe((data)=>{
    this.user=data;
      console.log("Find user",this.user);
    })
}
onSubmit(){
  console.log("Shoes to Edit",this.user);
  this.editUser();
}
}

