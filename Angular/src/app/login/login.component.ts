import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { UserProfileBean } from 'src/UserProfileBean';
import { SessionStorage } from 'ngx-webstorage';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

@Input()
@SessionStorage('user') 
Profile: UserProfileBean



  constructor(private fb: FormBuilder, private _http: HttpClient,private router:Router, private authService: AuthService) { }

  ngOnInit() {
  }



  _midURL: any = 'https://f8164a28-ad7a-49a8-873a-3928781ecee1.mock.pstmn.io/user/in';
     //END POINT REQUIRED TO CALL LOGIN METHOD FROM JAVA

  loginForm = this.fb.group({
    username:'Username',
    password: 'Password',
  })



  login(){
      console.log(this.loginForm.value);
      this._http.post(this._midURL, this.loginForm.value)
      .subscribe(
        response => console.log('success' , this.Profile=response),
        error => console.log('error', error)
      );
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
