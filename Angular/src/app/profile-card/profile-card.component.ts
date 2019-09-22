import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { SessionStorageService } from 'ngx-webstorage';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-card',
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.css']
})
export class ProfileCardComponent implements OnInit {
 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @Input()
  userProfile:any;
  isEdit: any;


  @Output()
  public editEvent = new EventEmitter();


  onEditEvent(){
    this.editEvent.emit();
      }
  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  
 profile: any;

constructor(private session:SessionStorageService, private router:Router){}



  ngOnInit() {
    this.retrieveSessionUser();     
  }


  retrieveSessionUser(){
    this.profile = this.session.retrieve('user');
  }


  redirectToEdit(){
    this.router.navigateByUrl('/editProfile');
  }

}
