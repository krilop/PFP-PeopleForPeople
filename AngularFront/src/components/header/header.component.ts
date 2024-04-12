import { Component } from '@angular/core';
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  constructor(private router:Router) {
  }

  logOut()
  {
    HeaderComponent.logout(this.router);
  }
  public static logout(router:Router)
  {
    localStorage.clear();
    router.navigateByUrl('/');
  }

  navigateToProfile(): void {
    const userId = localStorage.getItem('id'); // Замените на реальный id пользователя
    this.router.navigate(['/profile', userId]);
  }
}
