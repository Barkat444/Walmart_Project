import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from './auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

   constructor(private _loginService:AuthService){}
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        
    let newReq=req;
    let token=this._loginService.getToken();

        if(token!=null){
            newReq=newReq.clone({setHeaders:{Authorization:`Bearer ${token}`}})
        }   
        return next.handle(newReq);
     }
    
}