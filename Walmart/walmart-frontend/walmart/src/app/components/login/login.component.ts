import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

public formGroup= new FormGroup({});
public errorMessage:string="Login Failed";
public successMessage:string="Login Successful";
public invalidLogin:boolean=false;
public loginSuccess:boolean=false;

constructor(private form:FormBuilder,private route:Router,private http:HttpClient,private auth:AuthService) {
  this.formGroup=this.form.group({
    email:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required]),
    checkBox:new FormControl('')});  
}//constructor
//Validators.pattern('[a-z0-9]+@[a-z]+\.[a-z]{2,3}')]

get email() {
return this.formGroup.get('email');
}
get password() {
return this.formGroup.get('password');
}
get checkBox() {
return this.formGroup.get('checkbox');
}

onLogin(){
  let credentials={
    username:this.formGroup.value.email,
    password:this.formGroup.value.password
  }
    this.auth.doLogin(credentials).subscribe(res=>{
       this.loginSuccess=true;
         this.invalidLogin=false;
         this.auth.loginUser(res);
         alert("Welcome "+res.username);

         window.location.href="/";
      },error=>{
        console.log(error);
        this.loginSuccess=false;
         this.invalidLogin=true;
       });     
  }

  oAuthLogin(){
    this.auth.oAuthLoginService();
    alert("Welcome User");
    window.location.href="/";
  }
  
ngOnInit(): void {
}

}
