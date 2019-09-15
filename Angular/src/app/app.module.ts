import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';
import { FeedComponent } from './feed/feed.component';
import { PostComponent } from './post/post.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ProfileCardComponent } from './profile-card/profile-card.component';
import { PostCardComponent } from './post-card/post-card.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { ReactiveFormsModule}  from '@angular/forms';
import { FormsModule} from '@angular/forms';

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
    EditProfileComponent,
    
  ],
  
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
     
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
