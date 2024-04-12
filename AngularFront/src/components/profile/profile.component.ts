import { Component } from '@angular/core';
import {HeaderComponent} from "../header/header.component";
import {UserService} from "../../services/user-info/user.service";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {InterestProfileComponent} from "../interest-profile/interest-profile.component";
import {CommonModule} from "@angular/common";
import {filter, Subscription} from "rxjs";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    HeaderComponent,
    InterestProfileComponent,
    CommonModule
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  user: any;
  userIdFromRoute: string | null ='';
  userIdFromStorage: string | null ='';
  showEditButton: boolean = false;
  routerSubscription: Subscription | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userDataService: UserService
  ) {
    this.userIdFromStorage = localStorage.getItem('id');
    this.routerSubscription = this.listenToRouteChanges();
  }

  ngOnDestroy(): void {
    if (this.routerSubscription) {
      this.routerSubscription.unsubscribe();
    }
  }

  private listenToRouteChanges(): Subscription {
    return this.router.events
      .pipe(

        filter(event => event instanceof NavigationEnd)
      )
      .subscribe(() => {
        this.userIdFromRoute = this.route.snapshot.paramMap.get('id');
        this.showEditButton = this.userIdFromRoute === this.userIdFromStorage;
        this.loadUser();
      });
  }

  private loadUser(): void {
    if (this.userIdFromRoute) {
      this.userDataService.getUser(this.userIdFromRoute).subscribe({
        next: (data) => {
          this.user = data;
        },
        error: (err) => {
          console.error('Error fetching user:', err);
          // Возможно, здесь стоит перенаправить пользователя на другую страницу или показать сообщение об ошибке
        }
      });
    }
  }

  navigateToReg2(): void {
    this.router.navigateByUrl('registration/part2');
  }
}

