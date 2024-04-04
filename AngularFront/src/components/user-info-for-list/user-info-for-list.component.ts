import {Component, Input} from '@angular/core';
import {UserInfoDTO} from "../../DTO/UserInfoDTO";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-user-info-for-list',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './user-info-for-list.component.html',
  styleUrl: './user-info-for-list.component.css'
})
export class UserInfoForListComponent {
  @Input() user!: UserInfoDTO;
}
