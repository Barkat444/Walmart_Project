import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';;

@Injectable({
  providedIn: 'root'
})
export class ApiDataService {

  constructor(private http:HttpClient) { }
  public searchProducts:any[]=[];
  private subject=new Subject<any>();
  private loader=new Subject<any>();

  private url:string="https://api.bluecartapi.com/request?api_key=D456B36C68174AB8BDA5ABF22800A1E4&type=search&search_term=";
   
 //To fetch data from External Api 
fetchData(search:string):Observable<any[]>{
  return this.http.get<any[]>(this.url+search);
  }

  sendSearchData(message:any){
    this.subject.next(message);
  }

  
  receiveSearchData():Observable<any>{
    return this.subject.asObservable();
  }

  sendLoader(message:boolean){
    this.loader.next(message);
  }

  receiveLoader():Observable<boolean>{
    return this.loader.asObservable();
  }

}