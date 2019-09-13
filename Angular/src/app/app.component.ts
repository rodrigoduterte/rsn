import { Component } from '@angular/core';
<<<<<<< HEAD
import { HttpClient } from '@angular/common/http';
=======
>>>>>>> bfe0fda3e0268c4b89b8cbbc7721f921f6329aed

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
<<<<<<< HEAD
  title = 'Spring Mvc Angular Tutorial';

  // Object to save the response returned from the service.
  myresponse: any;

  // Url to fetch the employee records from the spring application.
  readonly APP_URL = 'http://localhost:8082/Springmvcangular';

  constructor(private _http: HttpClient) { }

  // Method to fetch all employees from the database table.
  getAllEmployees() {
    this._http.get(this.APP_URL + '/getemployees').subscribe(
      data => {
        this.myresponse = data;
      },
      error => {
        console.log('Error occured', error);
      }
    );
  }
=======
  title = 'Project2SocialNetwork';
>>>>>>> bfe0fda3e0268c4b89b8cbbc7721f921f6329aed
}
