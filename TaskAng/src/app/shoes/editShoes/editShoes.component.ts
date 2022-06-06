import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ShoesServiceService } from '../service/shoesService.service';
import { Shoes } from '../shoes';


@Component({
  selector: 'app-editShoes',
  templateUrl: './editShoes.component.html',
  styleUrls: ['./editShoes.component.css']
})
export class EditShoesComponent implements OnInit {
  id!:number;
  shoes:Shoes=new Shoes();

  constructor(private service:ShoesServiceService,private router:Router,private path:ActivatedRoute) { }

  ngOnInit() {
    this.infoShoes();
  }
  goToList(){
    this.router.navigate(['/shoes']);
  }
editShoes(){
  this.id=this.path.snapshot.params['id'];
  this.service.infoShoes(this.id).subscribe((data)=>{
    this.shoes=data;
      console.log("Find shoes",this.shoes);
    })
  this.service.editShoes(this.id,this.shoes).subscribe((data)=>{
    console.log("Edited:",data);
    this.goToList();
  })
}
infoShoes(){
  this.id=this.path.snapshot.params['id'];
  this.service.infoShoes(this.id).subscribe((data)=>{
    this.shoes=data;
      console.log("Find shoes",this.shoes);
    })
}
onSubmit(){
  console.log("Shoes to Edit",this.shoes);
  this.editShoes();
}
}
