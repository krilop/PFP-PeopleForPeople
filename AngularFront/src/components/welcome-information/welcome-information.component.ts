import { Component } from '@angular/core';

@Component({
  selector: 'app-welcome-information',
  standalone: true,
  imports: [],
  template: `
    <div class="welcome-container">
      <h1>Welcome to PFP - People for People</h1>
      <p>We're thrilled to have you join our community!</p>
      <p>Start exploring and connecting with amazing people.</p>
    </div>
  `,
  styles: [
    `
      .welcome-container {
        text-align: center;
        margin-top: 50px;
      }

      h1 {
        font-size: 2.5em;
        margin-bottom: 20px;
      }

      p {
        font-size: 1.2em;
        margin-bottom: 10px;
      }
    `
  ]
})
export class WelcomeInformationComponent {

}
