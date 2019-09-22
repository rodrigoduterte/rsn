import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { FormBuilder } from '@angular/forms';


@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.css']
})
export class ForgotComponent implements OnInit {

  constructor(private fb: FormBuilder,private router:Router, private _httpService: RegistrationService) { }


  resetPassword = this.fb.group({
    username: [''],
    //email: ['']
  });



  ngOnInit() {
  }
stuff: any;

  resetPasswordFunc(){
    console.log("Hit the Button!");

  this._httpService.getUserProfile(this.resetPassword.value.username)
  .subscribe(
    response=> this.stuff = response,
    error=> console.log(error));
    //console.log(response);
    console.log(this.stuff);
  }


}
