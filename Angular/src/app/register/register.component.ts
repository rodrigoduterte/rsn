import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

import { RegistrationService } from '../registration.service';
import { Router } from '@angular/router';
import {MatDatepickerModule} from '@angular/material/datepicker';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  // @Input()
  // Profile: UserProfileBean;
response: any;
  constructor(private router:Router,private fb: FormBuilder, 
    private _registrationService: RegistrationService) { }
  
  registrationForm = this.fb.group({
  username: ['', [Validators.required, Validators.minLength(4)]],
  password: ['', [Validators.required, Validators.minLength(8)]],
  //confirmPassword: ['', [Validators.required, Validators.minLength(4)]],
  firstName: ['', Validators.required],
  lastName: ['',Validators.required],
  dob: ['',Validators.required],
  gender: ['',Validators.required],
  bio: ['',Validators.required],
  email: ['',Validators.required]
},);

onSubmit(){
  console.log(this.registrationForm.value);
  this.registrationForm.setValue;
  this._registrationService.register(this.registrationForm.value)
  .subscribe(
    response => {
      console.log('success' , response);
      this.response = response.text;
      if (response.text == 'Registration successful') {
        this.router.navigateByUrl('/');
      } else {
        this.registrationForm.reset();
      }
    },
    error => {
      console.log('error', error);
      this.response = error.error.text;
      if (error.error.text == 'Registration successful') {
        this.router.navigateByUrl('/');
      } else {
        this.registrationForm.reset();
      }
    }
  );
}

  ngOnInit() {
  }

}
