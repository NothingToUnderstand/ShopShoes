import { Component, OnInit } from '@angular/core';
import {  ActivatedRoute, Router } from '@angular/router';
import { ResponseShoes } from '../ResponseShoes';
import { ShoesServiceService } from '../service/shoesService.service';
import { Shoes } from '../shoes';






@Component({
  selector: 'app-listShoes',
  templateUrl: './listShoes.component.html',
  styleUrls: ['./listShoes.component.css'],
  providers:[ShoesServiceService]
})
export class ListShoesComponent implements OnInit {
 
  responseShoes=new ResponseShoes();
 
  shoes!:Shoes[];
  name:string="";

  numberOfPages!:any;
  
  pageNo:number=1;
  totalItems!: number;
  totalPages!: number;
  pageSize:number=5;
  sortField:string="id";
  sortDir: string="asc";
  item!:number;
  status:boolean=false;

 
  constructor(private service:ShoesServiceService,private router:Router,private path:ActivatedRoute) { }

  ngOnInit() {
  this.getShoesData();
  this.sort();
  }

responseData(data:any){
  this.responseShoes=data;
  this.shoes=this.responseShoes.listShoes;
   this.totalItems=this.responseShoes.totalItems;
   this.totalPages=this.responseShoes.totalPages;
}
  getShoesData() {
  this.service.getShoesListData().subscribe(data=>{
  this.responseData(data)
    console.log("List", this.shoes);
  })
  }

  deleteShoes(id:number){
this.service.deleteShoes(id).subscribe((data)=>{
  console.log(data);
  console.log("delete is done");
this.getShoesData();
})
  }

  editShoes(id:number){
    this.router.navigate(['/editShoes',id]);

  }
  infoShoes(id:number){
    this.router.navigate(['/infoShoes',id]);
    
  }
  search(){
    if(this.name==""){
      this.getShoesData()
      console.log("name1",this.name)
    }else{
       this.service.searchShoes(this.name).subscribe((data)=>{
    this.shoes =data;
    console.log("name2",this.name)
      console.log("Find", this.shoes)
    })
    }
   
  }

sort(){
this.service.sort(this.pageNo,this.pageSize,this.sortField,this.sortDir).subscribe(data=>{
  this.responseData(data);
  this.allPages();
})
 
}


 pageNum(item:number){
  this.pageNo=item;
console.log("pageNo",this.pageNo);
this.sort();
}


allPages(){
   this.numberOfPages = [...Array(this.totalPages).keys()].map(x => ++x);
    console.log("Number of Pages",this.numberOfPages);
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
