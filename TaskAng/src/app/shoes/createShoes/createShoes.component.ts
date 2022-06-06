import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ShoesServiceService } from '../service/shoesService.service';
import { Shoes } from '../shoes';



@Component({
  selector: 'app-createShoes',
  templateUrl: './createShoes.component.html',
  styleUrls: ['./createShoes.component.css'],
  providers:[ShoesServiceService]
})
export class CreateShoesComponent implements OnInit {
  shoes:Shoes=new Shoes();

  constructor(private service:ShoesServiceService,private router:Router) { }

  ngOnInit() {
  }
  goToList(){
    this.router.navigate(['/shoes']);
  }

  saveShoes(){
    this.service.createShoes(this.shoes).subscribe((data)=>{
      console.log("Created:",data);
      this.goToList();
    })
  }

  onSubmit(){
console.log(this.shoes);
this.saveShoes();

  }
}



