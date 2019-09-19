import { Component, OnInit, Input } from '@angular/core';
import { UserProfileBean } from 'src/UserProfileBean';
import { SessionStorageService } from 'ngx-webstorage';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-card',
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.css']
})
export class ProfileCardComponent implements OnInit {
 
@Input()
profile: UserProfileBean;

constructor(private session:SessionStorageService, private _http: HttpClient,private router:Router){}

  

  ngOnInit() {
    this.retrieveSessionUser(); 
    
  }

  retrieveSessionUser(){
    this.profile = this.session.retrieve('user');
  }

  editProfile(){
    this.router.navigateByUrl('/edit-profile');
  }

  makeAPost(){
    this.router.navigateByUrl('/app-make-post');
  }

}
