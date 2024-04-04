import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {AuthorizationComponent} from "../components/authorization/authorization.component";
import {WelcomeInformationComponent} from "../components/welcome-information/welcome-information.component";
import {FormsModule} from "@angular/forms";
import {HttpClient, HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, AuthorizationComponent, WelcomeInformationComponent,FormsModule, HttpClientModule ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'AngularFront';
}
