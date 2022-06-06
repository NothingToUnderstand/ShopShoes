import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ShoesServiceService } from '../service/shoesService.service';
import { Shoes } from '../shoes';


@Component({
  selector: 'app-shoesInfo',
  templateUrl: './shoesInfo.component.html',
  styleUrls: ['./shoesInfo.component.css']
})
export class ShoesInfoComponent implements OnInit {
id!:number;
sh=new Shoes();
  constructor(private service:ShoesServiceService,private router:Router,private path:ActivatedRoute) { }

  ngOnInit() {
    this.infoShoes();
  }
infoShoes(){
  this.id=this.path.snapshot.params['id'];
  this.service.infoShoes(this.id).subscribe((data)=>{
  this.sh=data;
    console.log("Find shoes",this.sh);
  })
}
}
