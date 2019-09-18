import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { registerLocaleData } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

_url = 'http://localhost:9005/Springmvcangular' //need valid project name

  constructor(private _http: HttpClient) { }


register(userData){
 return this._http.post<any>(this._url + '/user/new', userData);
}

login(loginForm){
  return this._http.post<any>(this._url + '/user/in', loginForm);
}

getUserProfile(username){
  return this._http.get<any>(this._url + '/user/'+ username);
}

editProfile(userData){
  return this._http.post<any>(this._url + '/user/new', userData);
 }

}