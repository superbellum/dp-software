import {Component, input} from '@angular/core';
import {Candidate} from '../../../model/candidate.model';

@Component({
  selector: 'app-candidate-card',
  imports: [],
  templateUrl: './candidate-card.component.html',
  styleUrl: './candidate-card.component.css'
})
export class CandidateCardComponent {
  candidate = input.required<Candidate>();
}
