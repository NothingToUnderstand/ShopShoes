import { Component, OnInit } from '@angular/core';
import {  ActivatedRoute, Router } from '@angular/router';
import {  UserServiceService } from '../service/userService.service';
import { User } from '../user';

@Component({
  selector: 'app-listUsers',
  templateUrl: './listUsers.component.html',
  styleUrls: ['./listUsers.component.css'],
  providers:[UserServiceService]
})

export class ListUsersComponent implements OnInit {

  user!:User[];

   name!:string;

  numberOfPages!:any;
  totalItemsPerPage!: number;
  pageNo:number=1;
  totalItems!: number;
  totalPages: number=1;
  pageSize:number=5;
  sortField:string= "id";
  sortDir!: string;
  item!:number;
  status:boolean=false;
  constructor(private service:UserServiceService,private router:Router,private path:ActivatedRoute) { }

  ngOnInit() {
    this.getUserData();
  this.sort();
  }
  
  getUserData() {
    this.service.getUserListData().subscribe(data=>{
      this.user=data;
      this.totalItems=data.length;
      console.log("TotalItems",this.totalItems)
      console.log("List",this.user);
      
    })
    }
  
    deleteUser(id:number){
  this.service.deleteUser(id).subscribe((data)=>{
    console.log(data);
    console.log("delete is done");
  this.getUserData();
  })
    }
  
    editUser(id:number){
      this.router.navigate(['/editUser',id]);
  
    }
    infoUser(id:number){
      this.router.navigate(['/infoUser',id]);
      
    }
    search(){
      this.service.searchUser(this.name).subscribe((data)=>{
        this.user=data;
        console.log("Find", data);
      })
    }
  
  sort(){
  this.service.sort(this.pageNo,this.pageSize,this.sortField,this.sortDir).subscribe(data=>{
    this.user=data;
   
    this.allPages()
  
  })
  }
   pageNum(item:number){
    this.pageNo=item;
  console.log("pageNo",this.pageNo);
  this.sort();
  }
  
  allPages(){
    let result=this.totalItems%this.pageSize
    console.log("totalItems",this.totalItems)
    console.log("pageSize",this.pageSize)
     if(result==0){
       this.totalPages=this.totalItems/this.pageSize
     }else{
       this.totalPages=Math.floor(this.totalItems/this.pageSize+1);
     }
     console.log("TotalPages",this.totalPages)
     this.numberOfPages = [...Array(this.totalPages).keys()].map(x => ++x);
       return this.numberOfPages;
     }
   fieldName($event: string){
     this.sortField=$event;
     this.status=!this.status;
    if(this.status){
  this.sortDir="asc";
    }else{
      this.sortDir="desc";
    }
     console.log("sortDir",this.sortDir);
     console.log("sortField", this.sortField);
  this.sort();
   }
   

}
