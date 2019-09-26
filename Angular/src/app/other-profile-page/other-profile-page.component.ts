import { Component, OnInit } from '@angular/core';
import { SessionStorageService } from 'ngx-webstorage';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-other-profile-page',
  templateUrl: './other-profile-page.component.html',
  styleUrls: ['./other-profile-page.component.css']
})
export class OtherProfilePageComponent implements OnInit {

  constructor(private session:SessionStorageService, private http:RegistrationService) { }
otherProfile: any;
userPosts: any;
post: any;
  ngOnInit() {
    this.retrieveSessionUser();
  }

  retrieveSessionUser(){
    this.otherProfile = this.session.retrieve('searchedUser');
    console.log(this.otherProfile);
    this.getUserPosts();
  }

  getUserPosts() {
    this.http.getUserPosts(this.otherProfile.username)
    .subscribe(
      data => {this.userPosts = data;},
      error => {console.log('Error occured', error);
      })
      console.log(this.userPosts);
  }

}
