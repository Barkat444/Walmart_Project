import { Component, OnInit } from '@angular/core';
import { WishlistService } from 'src/app/services/wishlist.service';
import { ApiDataService } from '../../services/api-data.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private _apiGet:ApiDataService,private _cart:WishlistService) { }
  products: any;
  loading:boolean=false;
  fav:boolean=false;
  ngOnInit(): void {
    this._apiGet.receiveLoader().subscribe(loadingStatus=>this.loading=loadingStatus);

    this._apiGet.receiveSearchData().subscribe((data)=>{
      this.loading=false;
      this.products=data;
    }); 
  }

  //method to add product to wishlist
  addToWishList(p:any){
    this._cart.addItem(p).subscribe(res=>{
      console.log(res);
      alert("product added to wishlist");
    },error=>{
      alert("product already exist in wishlist!"+error);
    });
  }

//method to click category 
  onClickCategory(category:string){
    console.log(category);
    this._apiGet.sendLoader(true);
    this._apiGet.fetchData(category).subscribe(data=>{console.log(data) 
      this.loading=false;
      this.products=data;      
  });
}

}
