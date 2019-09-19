import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { UserProfileBean } from 'src/UserProfileBean';
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



  profile: any;
  


@SessionStorage('user')
userProfile: any;

  
  constructor(private fb: FormBuilder, private _http: HttpClient,private router:Router,
     private authService: AuthService, private _httpService: RegistrationService,
     private session: SessionStorageService) { }


     saveValue(){
       this.session.store('user',this.userProfile);
     }
  ngOnInit() {
  }

APP_URL: any = 'http://localhost:9005/Springmvcangular';
     

  loginForm = this.fb.group({
    username: [''],
    password: ['',]
  });
  username: any;

  

  // login(){
  //     console.log(this.loginForm.value.username);
  //     this._httpService.login(this.loginForm.value)
  //     .subscribe(
  //       response => console.log('successful connection:' ,  response),
  //       error => console.log('error', error)
  //     );
  //   //console.log(this.profile);
  //   this.getUser()
  // }
  
  
  
  loginValidate(){
    if((this.userProfile.username === this.loginForm.value[1]) && (this.userProfile.password === this.loginForm.value[2])){
      console.log(this.userProfile);
      console.log("credentials are good");
      this.onLogin();
      this.router.navigateByUrl('/feed');
    }else{
      console.log("Login Failed");
    }
    }

  getUser(){
     this._httpService.getUserProfile(this.username)
     .subscribe(
       response=> this.userProfile=response,
       error=> console.log(error));
       console.log(this.username);
       console.log(this.userProfile);
       setTimeout(this.loginValidate,3000); 
       console.log(this.userProfile);
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
