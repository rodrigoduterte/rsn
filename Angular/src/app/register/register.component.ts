import { Component, OnInit, Input } from '@angular/core';
import { UserProfileBean } from 'src/UserProfileBean';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { passwordMatchValidator } from '../shared/password.validator';
import { RegistrationService } from '../registration.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  // @Input()
  // Profile: UserProfileBean;

 

  constructor(private router:Router,private fb: FormBuilder, private _registrationService: RegistrationService) { }
  
  registrationForm = this.fb.group({
  username: ['', [Validators.required, Validators.minLength(4)]],
  password: ['', [Validators.required, Validators.minLength(4)]],
  confirmPassword: ['', [Validators.required, Validators.minLength(4)]],
  firstName: ['', Validators.required],
  lastName: ['',Validators.required ],
  email: ['',Validators.required]
}, {validator: passwordMatchValidator});

onSubmit(){
  console.log(this.registrationForm.value);
  this._registrationService.register(this.registrationForm.value)
  .subscribe(
    response => console.log('success' , response),
    error => console.log('error', error)
  );
  this.router.navigateByUrl('/')
}

  //newProfile = new UserProfileBean("Username", "Password", "First Name", "Last Name", "Email");

  // registrationForm = new FormGroup({
  //   username: new FormControl('Username'),
  //   password: new FormControl('Password'),
  //   firstName: new FormControl('First Name'),
  //   lastName: new FormControl('Last Name'),
  //   email: new FormControl('Email'),
  // });

  ngOnInit() {
  }

}
