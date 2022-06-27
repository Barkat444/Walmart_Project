import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserData } from '../models/UserData';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _http: HttpClient) { }

   private url:string="http://localhost:8999";
  
   //Create a new User
   createNewUser(credentials:UserData):Observable<any>{
    return this._http.post<UserData>(`${this.url}/user/registerUser`,credentials);
  }
  
  //Update an existing user
  updateUser(data:any,email:any):Observable<any>{
    const headers = { 'content-type': 'application/json'}
      return this._http.put<any>(this.url+`/user/update/${email}`,data,{'headers':headers});
   }

  //Delete an existing user 
  deleteUser(){}

}
