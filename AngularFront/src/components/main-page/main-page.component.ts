import { Component } from '@angular/core';
import {WelcomeInformationComponent} from "../welcome-information/welcome-information.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [
    WelcomeInformationComponent
  ],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})
export class MainPageComponent {

  constructor(private router:Router) { }

  goToRegistration() {
    this.router.navigate(['/auth/sign-up']);
  }

  goToAuthentication() {
    this.router.navigate(['/auth/sign-in']);
  }
}
