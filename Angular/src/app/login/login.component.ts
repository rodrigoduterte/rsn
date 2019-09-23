import { Component, OnInit, Input } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { SessionStorage, SessionStorageService } from 'ngx-webstorage';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private session:SessionStorageService, private fb: FormBuilder, private _http: HttpClient,private router:Router, private authService: AuthService, private _httpService: RegistrationService) { }

 
  


@SessionStorage('user')
userProfile: any;

saveValue() {
  this.session.store('user', this.userProfile);
}

profile: any;


  ngOnInit() {
  }

     

  loginForm = this.fb.group({
    username: [''],
    password: ['']
  });

  loginObject ={
    username:  this.loginForm.value.username,
    password: this.loginForm.value.password,
  }

  username: any;

  found: any;


  convertFormToObject(){
    this.loginObject.username = this.loginForm.value.username;
    this.loginObject.password = this.loginForm.value.password;
    this.login()
;  }

  login(){
      console.log(this.loginObject);
      this._httpService.login(this.loginObject)
      .subscribe(
        response => console.log( response),
        error => this.found = error.error.text)
        // error =>  LoginComponent.found=error.error.text)
      this.loginValidate()
      console.log(this.found)
      

    
  }
  
  
  
  loginValidate(){
    
    console.log(this.found);
    console.log("Before Validate: " + this.userProfile);
    
    
        if(this.found == "User found"){
          console.log("credentials are good");
          this.getUser();
    }else if(this.found =="Password Invalid"){
      //Make an Alrt Box pop up to tell user password is invalid
      console.log("Login Failed");
    }else if(this.found == "User not Activated"){
      //make an alert telling user to activate account vie email
    }else if(this.found == "User Not Found"){
      //make an alert suggesting that the user should create an account
    }
    }

  getUser(){
    
     this._httpService.getUserProfile(this.loginForm.value.username)
     .subscribe(
       response=> this.userProfile=response,
       error=> console.log(error));
     // setTimeout(this.loginValidate,3000); 
      console.log("From Database: " + this.userProfile);
      this.saveValue()
      this.onLogin();
      this.router.navigateByUrl('/profile');
      // this.onLogin() //Temperary, this will be moved later. We want to use this method in the loginValidate Method
  }


  onLogin(){
    console.log('hello');
    //this.string1 = "clicked";
    this.authService.loginFlag();
    
  }

  onLogout(){
    this.authService.logoutFlag();
  }

 

}
