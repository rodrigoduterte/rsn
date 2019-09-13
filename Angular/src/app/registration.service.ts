import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { registerLocaleData } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

_url = 'http://localhost:9005/STSProjectName' //need valid project name

  constructor(private _http: HttpClient) { }


register(userData){
 return this._http.post<any>(this._url, userData);
}


}