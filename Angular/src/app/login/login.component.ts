import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { UserProfileBean } from 'src/UserProfileBean';
import { SessionStorage } from 'ngx-webstorage';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {




// @Input()
// Profile: UserProfileBean


// @SessionStorage('user')
// username = this.Profile.username()

  profile: any;
  userProfile: any;
  constructor(private fb: FormBuilder, private _http: HttpClient,private router:Router, private authService: AuthService, private _httpService: RegistrationService) { }

  ngOnInit() {
  }



APP_URL: any = 'http://localhost:9005/Springmvcangular';
     

  loginForm = this.fb.group({
    username: ['username'],
    password: ['password',]
  });
  username: any;

  

  login(){
      console.log(this.loginForm.value.username);
      this._httpService.login(this.loginForm.value)
      .subscribe(
        response => console.log('success' ,  this.profile=response),
        error => console.log('error', error)
      );
    //console.log(this.profile);
    this.getUser()
  }
  
  // loginValidate(){
  //   if((this.Profile._username === this.loginForm.value[1]) && (this.Profile._username === this.loginForm.value[2])){
  //     this.login();
  //   }
  // }

  getUser(){
     this._httpService.getUserProfile(this.loginForm.value.username)
     .subscribe(
       response=> this.userProfile=response,
       error=> error);
       console.log(this.userProfile);
  }


  onLogin(){
    console.log('hello');
    //this.string1 = "clicked";
    this.authService.loginFlag();
    this.login();
    this.router.navigateByUrl('/feed');
  }
  onLogout(){
    this.authService.logoutFlag();
  }

}
