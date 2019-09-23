import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { SessionStorageService, SessionStorage } from 'ngx-webstorage';
import { RegistrationService } from '../registration.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-make-post',
  templateUrl: './make-post.component.html',
  styleUrls: ['./make-post.component.css']
})
export class MakePostComponent implements OnInit {

  constructor(private fb: FormBuilder, private session: SessionStorageService,private _http: RegistrationService, private router:Router) {
    this.retrieveSessionUser();
  }

    @SessionStorage('user')
    profile: any;

    
newPostObject: any;


  newPost = this.fb.group({
    body: '',
    photo: '',
    
  });

 makePostObject(){   //this method assigns the necesary values needed from the userProfile and the forms i the HTML to submit a new post to the Database.
  this.newPostObject ={
    photo: this.newPost.value.photo,
    body: this.newPost.value.body,
  }
   this.addPost(this.newPostObject);
 }

  ngOnInit() {
    this.retrieveSessionUser()
  }

  retrieveSessionUser(){
    this.profile = this.session.retrieve('user');
  }

  addPost(object: any){
    console.log(object);
    this._http.newPost(object,this.profile.username)
    .subscribe(
      response => console.log('success' , response),
      error => console.log('error', error)
    );
    this.router.navigateByUrl('/feed');

}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Photo Upload~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

async uploadPostPhoto(event) {
  let file = event.target.files[0];
  console.log(file);
​
​
  let urlResponse = await fetch('http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn/post/photo/' + this.profile.username, {
    method: 'PUT'
  });
  let signedUrl = await urlResponse.json();
  
  console.log(signedUrl);
​
  let s3Response = await fetch(signedUrl.postPhoto, {
    method: 'PUT',
    body: file
  })
​
  let urlResponse2 = await fetch('http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn/post/photo/' + this.profile.username +'?posti=' + signedUrl.postId, {
    method: 'GET'
  });
  let signedUrl2 = await urlResponse2.text();

  // let image = document.getElementById('file-img');
  // image.src = signedUrl2;
}
}
