import { Component,OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { RegistrationService } from '../registration.service';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';





@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {


  

  constructor(private fb: FormBuilder, private _registrationService: RegistrationService){}


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
      occupation: ['']
  })
  
  submitEdit(){
    this.editProfileForm.setValue;
    console.log(this.editProfileForm.value);
    this._registrationService.register(this.editProfileForm.value).subscribe(
      response => console.log('SUCCESS!!!!', response),
      error => console.error('Error...')
    )

  }

  ngOnInit() {
  }

}
