import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SessionStorageService, SessionStorage } from 'ngx-webstorage';
import { RegistrationService } from '../registration.service';



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
editMode: boolean = false;

editSwitchOn(){
  this.ngOnInit()
  this.editMode == true;
  console.log(this.editMode)
  }

  editSwitchOff(){
    this.editMode == false;
  }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


@SessionStorage('user')
userProfile:any;

  //need to write a method that fetches a single users information based on a search selection and fills in the profile object to fill the profile card. 
  userPosts: any;

  post:any;



  constructor(private _http: RegistrationService,private session:SessionStorageService) {
    this.retrieveSessionUser();
    this.getUserPosts();
   }


  
  ngOnInit() {
    this.retrieveSessionUser();
    this.getUserPosts();
  }

  retrieveSessionUser(){
    this.userProfile = this.session.retrieve('user');
  }

getUserPosts() {
  this._http.getUserPosts(this.userProfile.username)
  .subscribe(
    data => {this.userPosts = data;},
    error => {console.log('Error occured', error);
    })
    console.log(this.userPosts);
  
}

}
