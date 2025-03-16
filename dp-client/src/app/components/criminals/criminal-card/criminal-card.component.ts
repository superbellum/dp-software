import {Component, input} from '@angular/core';
import {Criminal} from '../../../model/criminal.model';

@Component({
  selector: 'app-criminal-card',
  imports: [],
  templateUrl: './criminal-card.component.html',
  styleUrl: './criminal-card.component.css'
})
export class CriminalCardComponent {
  criminal = input.required<Criminal>();


}
