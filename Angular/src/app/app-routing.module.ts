import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { FeedComponent } from './feed/feed.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from './auth.service';
import { AuthGuard } from './auth-gaurd.service';

import { EditProfileComponent} from './edit-profile/edit-profile.component';
import { MakePostComponent} from './make-post/make-post.component';


const routes: Routes = [
  {path: 'profile', canActivate: [AuthGuard], component: ProfileComponent},
  {path: 'feed', canActivate: [AuthGuard], component: FeedComponent},
  {path: 'register', component: RegisterComponent},
  {path: '', component: LoginComponent}, //login Page

  {path: 'edit-profile', canActivate: [AuthGuard], component: EditProfileComponent},
  {path: 'app-make-post', component: MakePostComponent},
  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
