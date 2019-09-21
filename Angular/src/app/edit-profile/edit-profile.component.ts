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

  

  @SessionStorage()
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
  
  submitEdit(){
    this.editProfileForm.setValue;
    console.log(this.editProfileForm.value);
    this._registrationService.editProfile(this.editProfileForm.value).subscribe(
      response => console.log('SUCCESS!!!!', response),
      error => console.error('Error...')
    );
    this.router.navigateByUrl('/profile');

  }

  retrieveSessionUser(){
    this.userProfile = this.session.retrieve('user');
  }

  ngOnInit() {
    this.retrieveSessionUser()
  }

}
