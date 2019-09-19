import { Component, OnInit, Input } from '@angular/core';
import { PostBean } from 'src/PostBean';
import { HttpClient } from '@angular/common/http';
import { UserProfileBean } from 'src/UserProfileBean';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  @Input()
  profile: UserProfileBean;

  //need to write a method that fetches a single users information based on a search selection and fill sin the profile object to fill the profile card. 
  userPosts: any;

  APP_URL = 'http://localhost:9005/STSProject';


  

  constructor(private _http: HttpClient) { }

  ngOnInit() {
  }

getUserPosts() {
  this._http.get(this.APP_URL + '/getUserPosts').subscribe(
    data => {
      this.userPosts = data;
    },
    error => {
      console.log('Error occured', error);
    }
  )
}

}
