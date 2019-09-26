import { Component,OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { RegistrationService } from '../app/registration.service';
import { SessionStorageService, SessionStorage } from 'ngx-webstorage';


import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from  '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Project2SocialNetwork';

  static hidden = {
    logout: true,
    profile: true,
    feed: true,
    search: true,
    register: false
  }

  constructor(private _authService: AuthService,
    private session:SessionStorageService,
    private modalService: NgbModal, 
    private formBuilder: FormBuilder, 
    private _http: RegistrationService
    ){

      this.searchForm  =  this.formBuilder.group({
        name: [''],
      });
      this.isValid = this._authService.loggedIn;
    }

  @SessionStorage('logStatus')
  isValid:any;
  


  logOut(){
    this.clearItem();
    this._authService.logoutFlag();
    this.isValid = this._authService.loggedIn;
    this.disableNavbar();
    return console.log(this.isValid);
    
  }

  clearItem() {
    this.session.clear('user');
  }

  getLogoutHidden() {
    return AppComponent.hidden.logout;
  }

  setLogoutHidden() {
    AppComponent.hidden.logout = !AppComponent.hidden.logout;
  }

  getProfileHidden() {
    return AppComponent.hidden.profile;
  }

  setProfileHidden() {
    AppComponent.hidden.profile = !AppComponent.hidden.profile;
  }

  getFeedHidden() {
    return AppComponent.hidden.feed;
  }

  setFeedHidden() {
    AppComponent.hidden.feed = !AppComponent.hidden.feed;
  }

  getSearchHidden() {
    return AppComponent.hidden.search;
  }

  setSearchHidden() {
    AppComponent.hidden.search = !AppComponent.hidden.search;
  }

  getRegisterHidden() {
    return AppComponent.hidden.register;
  }

  setRegisterHidden() {
    AppComponent.hidden.register = !AppComponent.hidden.register;
  }

  //~~~~~~~~~~~~~~~~~~~~~~~~~~Search Feature~~~~~~~~~~~~~~~~~~~~~~~

  closeResult: string;
  people: any;
  searchForm: FormGroup;
  search: any;
  
  ngOnInit() {

  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  disableNavbar() {
    AppComponent.hidden.logout = true;
    AppComponent.hidden.feed = true;
    AppComponent.hidden.profile = true;
    AppComponent.hidden.search = true;
    AppComponent.hidden.register = false;
  }

  submit() { 
    console.log(this.searchForm.value.name);
    this.showResults(  this.searchForm.value.name );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  showResults(searchKey: string) {
    this._http.showSearchResults(searchKey).subscribe(
      data => {
        this.people = data;
      },
      error => {
        console.log('Error occured', error);
      })
    // this._http.get(this.global.URL + '/user/all?n=' + searchKey)
    // .subscribe(
    //   data => {
    //     this.people = data;
    //   },
    //   error => {
    //     console.log('Error occured', error);
    //   })
  }
}

  //~~~~~~~~~~~~~~~~~~~~~~~~~Search Feature~~~~~~~~~~~~~~~~~~~~~~~


