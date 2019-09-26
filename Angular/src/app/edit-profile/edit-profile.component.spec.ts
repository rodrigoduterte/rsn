import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProfileComponent } from './edit-profile.component';

describe('EditProfileComponent', () => {
  let component: EditProfileComponent;
  let fixture: ComponentFixture<EditProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

async function uploadProfilePhoto(event) {
  let file = event.target.files[0];
  console.log(file);
  //window.test = event.target;
  let urlResponse = await fetch('http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn/user/photo/' + this.userProfile.username, {  //file.name
    method: 'PUT'
  });
  let signedUrl = await urlResponse.text();
  let s3Response = await fetch(signedUrl, {
    method: 'PUT',
    body: file
  })
  // let urlResponse2 = await fetch('http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn/user/photo/' + this.userProfile.username, {
  //   method: 'GET'
  // });
  // let signedUrl2 = await urlResponse2.text();
  // let image = document.getElementById('file-img');
  //image.src = signedUrl2;
}
