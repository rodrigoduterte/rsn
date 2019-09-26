import { Component, OnInit, Input, Output, EventEmitter, ViewChild} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { SessionStorageService, SessionStorage } from 'ngx-webstorage';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RegistrationService } from '../registration.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-make-post',
  templateUrl: './make-post.component.html',
  styleUrls: ['./make-post.component.css']
})
export class MakePostComponent implements OnInit {

  constructor(private fb: FormBuilder,
    private session: SessionStorageService,
    private _http: RegistrationService,
    private router: Router,
    private http: HttpClient
  ) { }
  
  URL = 'http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn';

  fileData: File = null;

  newPostObject: any;

  profile: any;

  newPost = this.fb.group({
    body: '',
    photo: '',

  });

  makePostObject(type, postId){   //this method assigns the necesary values needed from the userProfile and the forms i the HTML to submit a new post to the Database.
    this.newPostObject ={
      body: this.newPost.value.body,
    }
    if(type == 'add') {
      this.addPost(this.newPostObject);
    } else if (type == 'edit') {
      this._http.editPost(this.newPostObject, postId).subscribe(
        response => console.log('success', response),
        error => console.log('error', error)
      );
      this.router.navigateByUrl('/feed');
    }
   }

  addPostsAfterCurrentPost(post: any) {
  } 

  ngOnInit() {
    this.profile = this.session.retrieve('user').username;
  }

  addPost(json: any) {
    console.log(json);
    this._http.newPost(json)
      .subscribe(
        response => console.log('success', response),
        error => console.log('error', error)
      );
    this.router.navigateByUrl('/feed');
  }

  editPost(json: any) {
    console.log(json);
    this._http.newPost(json)
      .subscribe(
        response => console.log('success', response),
        error => console.log('error', error)
      );
    this.router.navigateByUrl('/feed');
  }

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Photo Upload~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  loadPhoto(event) {
    let file = event.target.files[0];
    //console.log(file);
    this.session.store('file', file);
    //console.log(this.session.retrieve('file'));
  }

  async sendPost() {
    let file = this.session.retrieve('file');
    console.log(file);
    if (file == null || (Object.entries(file).length === 0 && file.constructor === Object)) {
      this.makePostObject('add', null);
      this.newPost.reset();
    } else {
      let urlResponse = await fetch(this.URL+'/post/photo/' + this.profile, {
        method: 'PUT'
      });

      let signedUrl = await urlResponse.json();
      //console.log(signedUrl.postId);

      await fetch(signedUrl.postPhoto, {
        method: 'PUT',
        body: file
      });

      this.makePostObject('edit', signedUrl.postId);
      this.newPost.reset();
      this.router.navigateByUrl('/feed');
    }
  }


}
