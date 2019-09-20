import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { SessionStorageService } from 'ngx-webstorage';

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

constructor(private session:SessionStorageService){}



  ngOnInit() {
    this.retrieveSessionUser();     
  }


  retrieveSessionUser(){
    this.profile = this.session.retrieve('user');
  }

}
