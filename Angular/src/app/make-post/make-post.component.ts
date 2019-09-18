import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { SessionStorageService } from 'ngx-webstorage';
import { UserProfileBean } from 'src/UserProfileBean';

@Component({
  selector: 'app-make-post',
  templateUrl: './make-post.component.html',
  styleUrls: ['./make-post.component.css']
})
export class MakePostComponent implements OnInit {

  constructor(private fb: FormBuilder, private _http: HttpClient, private session: SessionStorageService) { }
  APP_URL = 'http://localhost:9005/Spring2';
  //newPost = new PostBean();


  @Input()
profile: UserProfileBean;


retrieveSessionUser(){
  this.profile = this.session.retrieve('user');
}

  newPost = this.fb.group({
    firstName: 'firstame' ,  //the goal is to fill in names and subtext amd profile picture via the session
    lastName: 'lastname' ,
    subTitle: 'subtitle',
    bodyText: 'bodytext',
    bodyImage: 'image',
    
  });

  // newPost = this.fb.group({          //Need getters in BEAN but this causes Session error Need to Solve
  //   firstName: this.profile._firstName,
  //   lastName: this.profile._lastName ,
  //   subTitle: this.profile._occupation,
  //   bodyText: 'bodytext',
  //   bodyImage: 'image',
    
  // });



  ngOnInit() {
    this.retrieveSessionUser()
  }

  addPost(){
    console.log(this.newPost);
    this._http.post<any>(this.APP_URL + '/post/new', this.newPost)
    .subscribe(
      response => console.log('success' , response),
      error => console.log('error', error)
    );

}
}
