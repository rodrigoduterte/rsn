import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { registerLocaleData } from '@angular/common';
import { SessionStorageService, SessionStorage } from 'ngx-webstorage';


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  headers = new HttpHeaders();
  username: string;
  URL = 'http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn';
  constructor(private _http: HttpClient,
    private session: SessionStorageService) { 
      //this.username = this.session.retrieve('user').username;
      this.headers.set('Content-Type', 'application/json; charset=utf-8');
    }

  getUsername() {
    return this.session.retrieve('user').username;
  }

  //TAlpsX8
  showSearchResults(searchKey) {
    return this._http.get<any>(this.URL + '/user/all?n=' + searchKey);
    
  }


  register(userData) {
    return this._http.post<any>(this.URL + '/user/new', userData);
  }

  login(loginForm) {
    return this._http.post<any>(this.URL + '/user/in', loginForm);
  }

  getUserProfile(username) {
    //console.log(this.username);
    return this._http.get<any>(this.URL + '/user/info/' + username);
  }

  editProfile(userData, username) {
    return this._http.post<any>('http://localhost:8083/Springmvcangular' + '/user/edit/' + username, userData);
  }

  newPost(json) {
    return this._http.post<any>(this.URL + '/post/new/' + this.getUsername(), json);
  }

  editPost(json, postId) {
    return this._http.post<any>(this.URL + '/post/edit/' + postId, json, {headers: this.headers});
  }

  getUserPosts(username) {
    return this._http.get<any>(this.URL + '/post/' + this.getUsername());
  }

  getAllPosts() {
    return this._http.get<any>(this.URL + '/post/all');
  }

  resetPassword(username) {
    return this._http.get<any>(this.URL + '/user/forgot/' + username)
  }

  likePost(username, postId) {
    return this._http.get<any>(this.URL + '/like/create/' + this.getUsername() + '/' + postId);
  }

  getPreSignedUrl(formData) {
    let url = this.URL + '/post/photo/' + this.getUsername() + '';
    return this._http.put<any>(url,formData);
  }

  uploadFile(preSignedUrl, fileData) {
    return this._http.put<any>(preSignedUrl, fileData);
  }
}