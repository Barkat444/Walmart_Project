import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';import { Item } from '../models/Item';
import { AuthService } from './auth.service';
;

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  constructor(private http:HttpClient,private auth:AuthService) { }

 
private url:string="http://localhost:8999";
public productCart = new Set();  
//public item: Item=new Item();

//add items to wishlist
addItem(data:any):Observable<any>{
  let item:Item=new Item();
  item.setImageUrl(data.product.main_image);
  item.setProductID(data.product.item_id);
  item.setRatings(data.product.rating);
  item.setTotalRatings(data.product.ratings_total);
  item.setPrice(data.offers.primary.price);
  item.setTitle(data.product.title);
  item.setShippingDays(data.fulfillment.shipping_days);
  
    const headers = { 'content-type': 'application/json'}
    let jsonItem=JSON.stringify(item);
   
    return this.http.post(this.url+"/wishlist/"+this.auth.getEmail(),jsonItem,{'headers':headers})
  }

//get all items from the spring-boot wishlist microservice 
  getItems(){
    return this.http.get<Item[]>(this.url+"/wishlist/"+this.auth.getEmail());
  }

//delete a particular item from spring boot server
  deleteItem(item:Item){
   console.log(item);
   return this.http.delete<number>(this.url+"/wishlist/"+this.auth.getEmail()+"/"+item.productId);
  }

}
