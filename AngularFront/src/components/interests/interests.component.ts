import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {InterestsService} from "../../services/interests/interests.service";
import {InterestDTO} from "../../DTO/InterestDTO";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-interests',
  standalone: true,
  imports: [
    FormsModule, CommonModule
  ],
  templateUrl: './interests.component.html',
  styleUrl: './interests.component.css'
})
export class InterestsComponent implements OnInit {
  interests: InterestDTO[] = [];
  newInterest: InterestDTO = { id: 0, titleOfType: '', icon: '' };
  showInterest: boolean = false;
  hoveredInterestName: string = '';

  constructor(private interestService: InterestsService, private router: Router) { }

  ngOnInit(): void {
    this.getInterests();
  }

  getInterests(): void {
    this.interestService.getInterestsForUser().subscribe(interests => {
      this.interests = interests;
    });
  }

  addInterestToUser(interestId: number): void {
    // Добавить интерес к пользователю
    console.log('Adding interest with ID:', interestId);
     this.interestService.addInterestForUser(interestId).subscribe(response => {
       console.log('Interest added successfully:', response);
     });
  }

  showInterestName(name: string): void {
    this.hoveredInterestName = name;
    this.showInterest = true;
  }

  hideInterestName(): void {
    this.showInterest = false;
  }

  addInterest(): void {
    console.log('Adding new interest:', this.newInterest);
     this.interestService.saveInterest(this.newInterest).subscribe(response => {
       console.log('New interest added successfully:', response);
       alert('New interest added successfully');
     });
  }

  finish(): void {
    this.router.navigate(['/profile']);
  }
}
