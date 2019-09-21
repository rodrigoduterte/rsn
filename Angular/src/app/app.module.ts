import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { FormsModule, ReactiveFormsModule } from '@angular/forms'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';
import { FeedComponent } from './feed/feed.component';
import { PostComponent } from './post/post.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ProfileCardComponent } from './profile-card/profile-card.component';
import { PostCardComponent } from './post-card/post-card.component';
import { RegistrationService } from './registration.service';
import { AuthService } from './auth.service';
import { AuthGuard } from './auth-gaurd.service';
import { MakePostComponent } from './make-post/make-post.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { ForgotComponent } from './forgot/forgot.component';

import { DatePickerComponent } from './date-picker/date-picker.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    FeedComponent,
    PostComponent,
    RegisterComponent,
    LoginComponent,
    ProfileCardComponent,
    PostCardComponent,
    MakePostComponent,
    EditProfileComponent,
    ForgotComponent,
    DatePickerComponent,
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [RegistrationService, AuthService,AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
