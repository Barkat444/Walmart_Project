import { Component, OnInit } from '@angular/core';
import { Item } from 'src/app/models/Item';
import { WishlistService } from 'src/app/services/wishlist.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  constructor(private _wishlist:WishlistService) { }

  public wishlist_products :Item[]=[];
   
   ngOnInit(): void {
    //get items onload 
    this._wishlist.getItems().subscribe(data=>this.wishlist_products=data);
  }

  //Delete product from wishlist
  deleteProduct(product:any){ 
    this._wishlist.deleteItem(product).subscribe();
    alert('product deleted');
    this._wishlist.getItems().subscribe(data=>this.wishlist_products=data);
  }
  

}
