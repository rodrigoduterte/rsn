import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';
import { FeedComponent } from './feed/feed.component';
import { PostComponent } from './post/post.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ProfileCardComponent } from './profile-card/profile-card.component';
import { PostCardComponent } from './post-card/post-card.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    FeedComponent,
    PostComponent,
    RegisterComponent,
    LoginComponent,
    ProfileCardComponent,
    PostCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
