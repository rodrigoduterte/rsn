import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { SessionStorageService, SessionStorage } from 'ngx-webstorage';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Project2SocialNetwork';

  constructor(private _authService: AuthService,private session:SessionStorageService){}

  isValid:any = this._authService.loggedIn;
  
  @SessionStorage('user')
  


  logOut(){
    this.clearItem();
    this._authService.logoutFlag();
    return console.log(this.isValid);
  }

  clearItem() {
    this.session.clear('user');
  }
}
