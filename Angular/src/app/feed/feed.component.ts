import { Component, OnInit, Input } from '@angular/core';
import { PostBean } from 'src/PostBean';
import { SessionStorageService } from 'ngx-webstorage';
import { UserProfileBean } from 'src/UserProfileBean';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

APP_URL = 'END POINT FOR GETALLPOSTS'
feedPosts: any;


  @Input()
  currentUser : UserProfileBean;


  
  newPost = new PostBean();
  //newPost = new PostBean(this.currentUser.profilePic, this.currentUser.firstName, this.currentUser.lastName);   //this is a post Object for creating new posts 

  constructor(private _http: HttpClient, private session:SessionStorageService) { }

  ngOnInit() {   
    this.retrieveSessionUser();

  }     
  
  retrieveSessionUser(){
    this.currentUser = this.session.retrieve('user');
  }

// addPost(post: PostBean){            this was being used for dummy Data in an Array from a service. Wont need once we get Database info
//   this.feedArray.unshift(post);
//   this.feedArray = this.feedArray.splice(0);
//   this.newPost = new PostBean();
// }

getAllPosts(){
  this._http.get(this.APP_URL + '/getAllPosts').subscribe(  //Need to match endpoint with Gabe on the Java End
    data => {
      this.feedPosts = data;
    },
    error => {
      console.log('Error occured', error);
    }
  )

}
}
