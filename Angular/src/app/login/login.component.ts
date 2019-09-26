import { Component, OnInit, Input } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { SessionStorage, SessionStorageService } from 'ngx-webstorage';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { RegistrationService } from '../registration.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginStatus;

  constructor(private session: SessionStorageService,
    private fb: FormBuilder, 
    private _http: HttpClient, 
    private router: Router, 
    private authService: AuthService, 
    private _httpService: RegistrationService) { }

  URL = this._httpService.URL + 'g';
  

  @SessionStorage('user')
  userProfile: any;

  saveValue(response: any) {
    this.session.store('user', response);
    //this.session.retrieve('user')
  }

  profile: any;

  ngOnInit() { }

  loginForm = this.fb.group({
    username: [''],
    password: ['']
  });

  loginObject = {
    username: null,//this.loginForm.value.username,
    password: null//this.loginForm.value.password,
  }

  username: any;

  found: any;


  convertFormToObject() {
    this.loginObject.username = this.loginForm.value.username;
    this.loginObject.password = this.loginForm.value.password;
    this.login();
  }

  login() {
    console.log(this.loginObject);
    this._httpService.login(this.loginObject)
      .subscribe(
        response => console.log(response),
        error => {
          this.loginValidate(error.error.text)
        })
  }

  enableNavbar() {
    AppComponent.hidden.logout = false;
    AppComponent.hidden.feed = false;
    AppComponent.hidden.profile = false;
    AppComponent.hidden.search = false;
    AppComponent.hidden.register = true;
  }

  loginValidate(found: string) {
    if (found == "User found") {
      this.getUser();
    } else {
      this.loginStatus = found;
    }
  }

  getUser() {
    this._httpService.getUserProfile(this.loginForm.value.username)
      .subscribe(
        response => {
          this.saveValue(response);
          this.enableNavbar();
          this.onLogin();
          this.router.navigateByUrl('/profile');
        },
        error => {
          console.log(error)
          this.getUser()
        });
  }

  onLogin() {
    this.authService.loginFlag();
  }

  onLogout() {
    this.authService.logoutFlag();
  }
}
