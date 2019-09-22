import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { FeedComponent } from './feed/feed.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from './auth.service';
import { AuthGuard } from './auth-gaurd.service';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { ForgotComponent } from './forgot/forgot.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { SearchComponent } from './search/search.component';
import { OtherProfilePageComponent } from './other-profile-page/other-profile-page.component';


const routes: Routes = [
  {path: 'profile', canActivate: [AuthGuard], component: ProfileComponent},
  {path: 'feed', canActivate: [AuthGuard], component: FeedComponent},
  {path: 'register', component: RegisterComponent},
  {path: '', component: LoginComponent}, //login Page
  {path: 'editProfile',canActivate: [AuthGuard], component: EditProfileComponent},
  {path: 'forgot', component: ForgotComponent},
  {path: 'search', canActivate: [AuthGuard], component: SearchComponent},
  {path: 'search-result', canActivate: [AuthGuard], component: SearchResultsComponent},
  {path: 'otherProfile', canActivate: [AuthGuard], component: OtherProfilePageComponent},
  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
