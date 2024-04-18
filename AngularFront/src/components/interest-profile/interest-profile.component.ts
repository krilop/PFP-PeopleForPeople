import {Component, OnInit} from '@angular/core';
import {InterestsService} from "../../services/interests/interests.service";
import {InterestDTO} from "../../DTO/InterestDTO";
import {CommonModule} from "@angular/common";
import {ActivatedRoute, Route} from "@angular/router";

@Component({
  selector: 'app-interest-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './interest-profile.component.html',
  styleUrl: './interest-profile.component.css'
})
export class InterestProfileComponent implements OnInit {
  interests: InterestDTO[] = [];
  userId: string = '';

  constructor(private interestsService: InterestsService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.userId = params['id'];
      this.getInterests();
    });
  }

  getInterests(): void {
    this.interestsService.getInterestsOfUser(this.userId).subscribe(interests => {
      this.interests = interests;
    });
  }
}
