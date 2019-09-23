import { Component, OnInit, Input } from '@angular/core';
import { PostBean } from 'src/PostBean';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})
export class PostCardComponent implements OnInit {

  constructor(private http:RegistrationService) { }

  @Input()
  post: any;
  @Input()
  userProfile: any;
  
  liked: any;

  

  
  ngOnInit() {
    this.liked="Like!"
  
  }

  likePost(){
    this.http.likePost(this.userProfile.username, this.post.post_id)
    .subscribe(
      response => console.log(response),
      error => this.liked = error.error.text)
      this.likeStat()
     
  }

  likeStat(){
    console.log(this.liked);
  }


}