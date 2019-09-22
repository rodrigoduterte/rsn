import { Component, OnInit, Input } from '@angular/core';
import { SessionStorage, SessionStorageService } from 'ngx-webstorage';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  @Input() people2: any;

//~~~~~~~~~~~~~~~~~~~~~~~~~~NEEDS TESTING~~~~~~~~~~~~~~~~~~
  @SessionStorage('searchedUser')
  
  
  saveValue() {
    this.session.store('searchedUser', this.people2);
  }

  loadSearchedProfile(){

  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~NEEDS TESTING~~~~~~~~~~~~~~~~~~

  constructor(private session: SessionStorageService) { }

  ngOnInit() {
    console.log(this.people2)
  }

 

}
