import { Component, OnInit, Input } from '@angular/core';
import { SessionStorageService, SessionStorage } from 'ngx-webstorage';
import { HttpClient } from '@angular/common/http';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

@SessionStorage('user')
userProfile: any;


feedPosts: any;





  
  
  //newPost = new PostBean(this.currentUser.profilePic, this.currentUser.firstName, this.currentUser.lastName);   //this is a post Object for creating new posts 

  constructor(private _http: RegistrationService, private session:SessionStorageService) {
    this.getAllPosts();
   }

  ngOnInit() {  
    
      document.body.classList.add('bg-img');
    this.retrieveSessionUser();

  }     
  
  retrieveSessionUser(){
    this.userProfile = this.session.retrieve('user');
  }

// addPost(post: PostBean){            this was being used for dummy Data in an Array from a service. Wont need once we get Database info
//   this.feedArray.unshift(post);
//   this.feedArray = this.feedArray.splice(0);
//   this.newPost = new PostBean();
// }

getAllPosts(){
  this._http.getAllPosts().subscribe( 
    data => {
      this.feedPosts = data;
    },
    error => {
      console.log('Error occured', error);
    }
  )

}
}
