import {Component, Input} from '@angular/core';
import {UserInfoDTO} from "../../DTO/UserInfoDTO";
import {DatePipe, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";


@Component({
  selector: 'app-user-info-for-list',
  standalone: true,
  imports: [
    DatePipe,
    FormsModule,
    NgIf
  ],
  templateUrl: './user-info-for-list.component.html',
  styleUrl: './user-info-for-list.component.css'
})
export class UserInfoForListComponent {
  @Input() user!: UserInfoDTO;

  constructor(private router:Router){}

  handleImageError(event: any) {
    event.target.src = '/assets/images/placeholder.png'; // Путь к запасному изображению
  }

  goToProfile(userId: number): void {
    this.router.navigate(['/profile', userId]);
  }
}
