import { Routes } from '@angular/router';
import { AuthorizationComponent } from "../components/authorization/authorization.component";
import { MainPageComponent } from "../components/main-page/main-page.component";
import { RegistrationComponent } from "../components/registration/registration.component";
import {UserListComponent} from "../components/user-list/user-list.component";
import {ProfileComponent} from "../components/profile/profile.component";
import {PostUserInfoComponent} from "../components/post-user-info/post-user-info.component";
import {InterestsComponent} from "../components/interests/interests.component";

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/main-page',
  },
  {
    path: 'main-page',
    title: 'Main page',
    component: MainPageComponent
  },
  {
    path: 'auth/sign-in',
    title: 'Authorization',
    component: AuthorizationComponent
  },
  {
    path: 'auth/sign-up',
    title: 'Registration',
    component: RegistrationComponent
  },
  {
    path: 'userlist',
    title: 'List of users',
    component: UserListComponent
  },
  {
    path: 'profile/:id',
    title: 'Profile',
    component: ProfileComponent
  },
  {
    path: 'registration/part2',
    title: 'Give some info about you',
    component: PostUserInfoComponent
  },
  {
    path: 'interests',
    title: 'OMG!! U R SO INTERESTING!',
    component: InterestsComponent
  },
  {
    path: '**',
    redirectTo: '/main-page', // Redirect to main page for any other path
  }
];
