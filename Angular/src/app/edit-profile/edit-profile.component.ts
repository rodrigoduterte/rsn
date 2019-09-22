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
      firstName: ['', Validators.required],
      middleName: ['',],
      lastName: ['', Validators.required],
      bio: [''],
      dob: [''],
      gender: [''],
      relationshipStatus: [''],
      favoriteColor: [''],
      city: [''],
      occupation: [''],
      email:['']
  })

  createFormFromUser(){
    this.editProfileForm.setValue({
      firstName: this.userProfile.firstName,
      middleName: this.userProfile.middleName,
      lastName: this.userProfile.lastName,
      bio: this.userProfile.bio,
      dob: this.userProfile.dob,
      gender: this.userProfile.gender,
      relationshipStatus: this.userProfile.relationshipStatus,
      favoriteColor: this.userProfile.favoriteColor,
      city: this.userProfile.city,
      occupation: this.userProfile.occupation,
      email:this.userProfile.email
    })
    console.log(this.editProfileForm.value);
  }
  
  submitEdit(){
    this.editProfileForm.setValue;
    console.log(this.editProfileForm.value);
    this._registrationService.editProfile(this.editProfileForm.value, this.userProfile.username)
    .subscribe(
      response => console.log('SUCCESS!!!!', response),
      error => console.error('Error...')
    );
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
    console.log(file);
    //window.test = event.target;
    let urlResponse = await fetch('http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn/user/photo/' + this.userProfile.username, {  //file.name
      method: 'PUT'
    });
    let signedUrl = await urlResponse.text();
    let s3Response = await fetch(signedUrl, {
      method: 'PUT',
      body: file
    })
    // let urlResponse2 = await fetch('http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn/user/photo/' + this.userProfile.username, {
    //   method: 'GET'
    // });
    // let signedUrl2 = await urlResponse2.text();
    // let image = document.getElementById('file-img');
    //image.src = signedUrl2;
  }

  



}
