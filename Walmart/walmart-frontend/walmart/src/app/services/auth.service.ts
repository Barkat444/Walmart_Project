import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
//public isLoggedIn=false;
public UserInfo:any;
private login=new Subject<any>();
  constructor(private http:HttpClient) { }
//  private url=" http://localhost:9194";
private url:string="http://localhost:8999";


//obsolete function
UpdateUser(data:any,email:any):Observable<any>{
  const headers = { 'content-type': 'application/json'}
    return this.http.put<any>(this.url+`/user/update/${email}`,data,{'headers':headers});
 }
  
   //
   doLogin(credentials:any):Observable<any>{
      return this.http.post<any>(this.url+"/user/token",credentials);
   }

   //service to store jwt token,username & email id in local storage
   loginUser(token:any){
    localStorage.setItem('token',token.jwt);
    localStorage.setItem('username',token.username);
    localStorage.setItem('email',token.email);
    return true;
   }

//Obsolete Service
  oAuthLoginService(){
    console.log('calling oAuth');
   
  }

   isLoggedInUser()
   {
     let token=localStorage.getItem('token');
     if(token==undefined||token===""){
       return false;
     }else{
       return true;
     }
   }

//service to logout user by removing jwt auth
   logoutUser(){
     localStorage.removeItem('token');
     localStorage.removeItem('email');
     localStorage.removeItem('username');
     return true;
   }
   //service to get token
  getToken(){
    return localStorage.getItem('token');
  }   
  //service to get username
  getUserName(){
    return localStorage.getItem('username');
  }   
  //service to get email
  getEmail(){
    return localStorage.getItem('email');
  }   

   
  sendLoginStatus(message:boolean){
    this.login.next(message);
  }

  
  receiveLoginStatus():Observable<boolean>{
    return this.login.asObservable();
  }
}
