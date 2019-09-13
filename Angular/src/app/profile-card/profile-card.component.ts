import { Component, OnInit, Input } from '@angular/core';
import { UserProfileBean } from 'src/UserProfileBean';
import { SessionStorageService } from 'ngx-webstorage';

@Component({
  selector: 'app-profile-card',
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.css']
})
export class ProfileCardComponent implements OnInit {
 
@Input()
profile: UserProfileBean;

constructor(private session:SessionStorageService){}

  

  ngOnInit() {
    this.retrieveSessionUser(); 
    
  }

  retrieveSessionUser(){
    this.profile = this.session.retrieve('user');
  }

}
