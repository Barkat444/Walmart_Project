import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, AsyncValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ConfirmedValidator } from '../register/ConfirmedValidator';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
 
  public flag:boolean=true;

  public formGroup= new FormGroup({});
  constructor(private form:FormBuilder,private route:Router,private http:HttpClient,private auth:AuthService,private userService:UserService) {
    
    this.formGroup=this.form.group({
      name:new FormControl(`${this.auth.getUserName()}`,[Validators.required,Validators.minLength(4)]),
      email:new FormControl({value:`${this.auth.getEmail()}`,disabled: true},[Validators.required,Validators.pattern('[a-z0-9]+@[a-z]+\.[a-z]{2,3}')]),
      password:new FormControl('',[Validators.required,Validators.minLength(8)]),
      passwordConfirm:new FormControl('',[Validators.required])
    },{
      validator: ConfirmedValidator()
   });  
  }//constructor
  
  ngOnInit(): void {
    
  } 
  
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


ShowData(){}

  onUpdate(){
    let credentials={
      username:this.formGroup.value.name,
      password:this.formGroup.value.password
    }
    let email=this.auth.getEmail();
    console.log(credentials);
    console.log(email); 
    this.auth.UpdateUser(credentials,email).subscribe(()=>{
    //  this.userService.updateUser(credentials,email).subscribe(()=>{  
    alert("User Updated Successfully!");         
         this.formGroup.reset();
         this.auth.logoutUser();
         window.location.href="/login";
        },error=>{
          alert("Error encountered During Registration.Please Retry");
        });
    }
    
}


