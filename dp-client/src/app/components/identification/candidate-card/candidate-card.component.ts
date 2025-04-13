import {Component, input} from '@angular/core';
import {CriminalCandidate} from '../../../model/criminal-candidate';

@Component({
  selector: 'app-candidate-card',
  imports: [],
  templateUrl: './candidate-card.component.html',
  styleUrl: './candidate-card.component.css'
})
export class CandidateCardComponent {
  candidate = input.required<CriminalCandidate>();
}
