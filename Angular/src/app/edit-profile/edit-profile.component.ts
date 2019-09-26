import { Component,OnInit} from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { RegistrationService } from '../registration.service';
import { Router } from '@angular/router';
import { SessionStorageService, SessionStorage } from 'ngx-webstorage';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  @SessionStorage('user')
  userProfile:any;

  constructor(private session:SessionStorageService, private fb: FormBuilder, private _registrationService: RegistrationService,private router:Router){}

  editProfileForm = this.fb.group({
      username: ['', Validators.required],
      email:[''],
      firstName: ['', Validators.required],
      middleName: ['',],
      lastName: ['', Validators.required],
      favoriteColor: [''],
      city: [''],
      relationshipStatus: [''],
      gender: [''],
      bio: ['', Validators.required],
      occupation: [''],
  })

  createFormFromUser(){
    this.editProfileForm.setValue({
      username: this.userProfile.username,
      firstName: this.userProfile.firstName,
      middleName: this.userProfile.middleName,
      lastName: this.userProfile.lastName,
      bio: this.userProfile.bio,
      gender: this.userProfile.gender,
      relationshipStatus: this.userProfile.relationshipStatus,
      favoriteColor: this.userProfile.favoriteColor,
      city: this.userProfile.city,
      occupation: this.userProfile.occupation,
      email:this.userProfile.email,
    })
    console.log(this.editProfileForm.value);
  }
  
  submitEdit(){
    let formobj = this.editProfileForm.getRawValue();
    console.log(formobj);
    console.log(JSON.stringify(this.editProfileForm.value));
    this._registrationService.editProfile(JSON.stringify(this.editProfileForm.value), this.userProfile.username)
    .subscribe(
      response => console.log('SUCCESS!!!!', response),
      error => console.error('Error...')
    );
    this.router.navigateByUrl('/feed');
    this.router.navigateByUrl('/profile');

  }

  retrieveSessionUser(){
    this.userProfile = this.session.retrieve('user');
  }

  ngOnInit() {
    this.retrieveSessionUser();
    this.createFormFromUser();
  }



   async uploadProfilePhoto(event) {
    let file = event.target.files[0];
    console.log(file); // http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn/user/photo/
    //window.test = event.target;// localhost:8083/Springmvcangular
    let urlResponse = await fetch('http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn/user/photo/' + this.userProfile.username, {  //file.name
      method: 'PUT'
    });
    let signedUrl = await urlResponse.text();
    let s3Response = await fetch(signedUrl, {
      method: 'PUT',
      body: file
    })
  }
}
