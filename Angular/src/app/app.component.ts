import { Component,OnInit } from '@angular/core';
import { AuthService } from './auth.service';
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

  constructor(private _authService: AuthService,private session:SessionStorageService
    
    ,private modalService: NgbModal, 
    private formBuilder: FormBuilder, 
    private _http: HttpClient
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
    this.isValid = this._authService.loggedIn
    return console.log(this.isValid);
    
  }

  clearItem() {
    this.session.clear('user');
  }





  //~~~~~~~~~~~~~~~~~~~~~~~~~~Search Feature~~~~~~~~~~~~~~~~~~~~~~~

  closeResult: string;
  people: any;
  searchForm: FormGroup;
  search: any;

  APP_URL = 'http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn';

  
  
      
    

  // ngOnInit() {
  //   this.searchForm  =  this.formBuilder.group({
  //     name: [''],
  //   });
  // }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
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
    this._http.get(this.APP_URL + '/user/all?n=' + searchKey)
    .subscribe(
      data => {
        this.people = data;
      },
      error => {
        console.log('Error occured', error);
    })
  }
}

  //~~~~~~~~~~~~~~~~~~~~~~~~~Search Feature~~~~~~~~~~~~~~~~~~~~~~~


