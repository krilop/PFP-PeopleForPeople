import {Component, OnInit} from '@angular/core';
import {UserInfoDTO} from "../../DTO/UserInfoDTO";
import {UserService} from "../../services/user-info/user.service";
import {UserInfoForListComponent} from "../user-info-for-list/user-info-for-list.component";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [
    UserInfoForListComponent, CommonModule
  ],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit {
  userList: UserInfoDTO[] | undefined;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe(
      users => {
        this.userList = users;
      },
      error => {
        console.error('Error loading users:', error);
      }
    );
  }
}
