import {Component, ViewChild} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {AuthorizationService} from "../../services/authorization/authorization.service";
import {Router} from "@angular/router";
import {CommonModule} from "@angular/common";


@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {
  @ViewChild('registrationForm') registrationForm!: NgForm;

  username: string = '';
  email: string = '';
  password: string = '';
  emailErrorMessages: { [key: string]: string } = {
    'required': 'Email is required.',
    'email': 'Invalid email format.'
  };

  constructor(private authorizationService: AuthorizationService, private router: Router) {}

  register() {
    this.authorizationService.register(this.username, this.email, this.password)
      .subscribe(
        response => {
          if (response.token) {
            localStorage.setItem('jwtToken', response.token);
            this.router.navigate(['/registration/part2']);
          }
        },
        error => {
          console.error('Registration error', error);
        }
      );
  }

  getEmailErrorMessage(): string {
    const emailControl = this.registrationForm.controls['email'];
    if (emailControl?.errors) {
      const errorKey = Object.keys(emailControl.errors)[0];
      return this.emailErrorMessages[errorKey];
    }
    return '';
  }
}
