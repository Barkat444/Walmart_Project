import { Component, OnInit} from '@angular/core';
import {AsyncValidatorFn, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import { ConfirmedValidator } from './ConfirmedValidator';
import { UserData } from '../../models/UserData';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public users:any[]=[];
  public user!: UserData; 
  public formGroup= new FormGroup({});
  ngOnInit(): void {
  }
  
  constructor(private form:FormBuilder,private route:Router,private _userService:UserService, private auth:AuthService){
    //this.user="azhar360thewhitewolf@gmail.com";
    this.formGroup=this.form.group({
      name:new FormControl('',[Validators.required,Validators.minLength(4)]),
      email:new FormControl('',[Validators.required,Validators.pattern('[a-z0-9]+@[a-z]+\.[a-z]{2,3}')],this.CheckEmail as AsyncValidatorFn),
      password:new FormControl('',[Validators.required,Validators.minLength(8)]),
      passwordConfirm:new FormControl('',[Validators.required]),
      checkBox:new FormControl('')},{
         validator: ConfirmedValidator()
      });  
  }//constructor
  
  
  get name() {
    return this.formGroup.get('name');
 }     
 
 get email() {
  return this.formGroup.get('email');
}
get password() {
  return this.formGroup.get('password');
}
get passwordConfirm() {
  return this.formGroup.get('passwordConfirm');
}
get checkBox() {
  return this.formGroup.get('checkbox');
}


CheckEmail(control:FormControl):Promise<any>|Observable<any>{

  let promise=new Promise((resolve,reject)=>{
     
    
   
    });
    return promise;
}

  onRegister(){
      this.user={
        username:this.formGroup.value.name,
        email:this.formGroup.value.email,
        password:this.formGroup.value.password,
       role:"USER"
      }
       this._userService.createNewUser(this.user).subscribe(()=>{
          alert("User Registered Successfully!");         
         this.formGroup.reset();
         this.route.navigate(['/login']);
        },error=>{
          alert("Error encountered During Registration.Please Retry");
        });
    }

    oAuthLogin(){
      this.auth.oAuthLoginService();
      alert("Welcome User");
      window.location.href="/";
    }
    

}
