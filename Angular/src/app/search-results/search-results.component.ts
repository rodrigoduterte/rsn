import { Component, OnInit, Input } from '@angular/core';
import { SessionStorage, SessionStorageService } from 'ngx-webstorage';
import { RegistrationService } from '../registration.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  @Input() people2: any;

//~~~~~~~~~~~~~~~~~~~~~~~~~~NEEDS TESTING~~~~~~~~~~~~~~~~~~
  @SessionStorage('searchedUser')
  
  userData: any;
 

  loadSearchedProfile(username){
   
    this.http.getUserProfile(username).subscribe(
      response=> this.userData = response,
      error=> console.log(error));
      this.saveValue();
  }

  saveValue() {
    console.log(this.userData);
    this.session.store('searchedUser', this.userData);
    this.redirectToClickedUser();
  }
redirectToClickedUser(){
  this.router.navigateByUrl('/otherProfile');
}


//~~~~~~~~~~~~~~~~~~~~~~~~~~NEEDS TESTING~~~~~~~~~~~~~~~~~~

  constructor(private session: SessionStorageService, private http: RegistrationService, private router:Router) { }

  ngOnInit() {
    console.log(this.people2)
  }

 

}
