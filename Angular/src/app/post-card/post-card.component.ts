import { Component, OnInit, Input } from '@angular/core';
import { PostBean } from 'src/PostBean';
import { SessionStorage, SessionStorageService } from 'ngx-webstorage';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})
export class PostCardComponent implements OnInit {

  constructor(private http:RegistrationService, 
    private session: SessionStorageService) { }

  @Input()
  post: any;
  @Input()
  userProfile: any;
  
  like: any;

  liked: boolean;

  ngOnInit() {
    this.liked = false;
    if(typeof this.post != 'undefined') {
      this.like = this.post.likes.find(obj => obj.username 
        == this.session.retrieve('user').username);
      console.log(this.like);
      if(typeof this.like != 'undefined') {
        this.liked = this.like.liked;
      }
    } 
  }

  likePost(){
    console.log(this.post);
    this.http.likePost(this.session.retrieve('user').username, this.post.post_id)
    .subscribe(
      response => console.log(response),
      error => this.liked = error.error.text == 'Post Liked' ? true : false)
  }

}