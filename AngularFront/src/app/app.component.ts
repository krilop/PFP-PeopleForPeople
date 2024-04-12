import {Component, inject} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {AuthorizationComponent} from "../components/authorization/authorization.component";
import {WelcomeInformationComponent} from "../components/welcome-information/welcome-information.component";
import {FormsModule} from "@angular/forms";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {CommonModule} from '@angular/common';
import {PostUserInfoComponent} from "../components/post-user-info/post-user-info.component";
import {InterestsComponent} from "../components/interests/interests.component";
import {InterestProfileComponent} from "../components/interest-profile/interest-profile.component";  // Import CommonModule

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, AuthorizationComponent, WelcomeInformationComponent, PostUserInfoComponent, FormsModule, HttpClientModule, CommonModule, InterestsComponent, InterestProfileComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'AngularFront';
}
