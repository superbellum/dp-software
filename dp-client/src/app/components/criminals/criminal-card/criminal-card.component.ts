import {Component, inject, input} from '@angular/core';
import {Criminal} from '../../../model/criminal.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-criminal-card',
  imports: [],
  templateUrl: './criminal-card.component.html',
  styleUrl: './criminal-card.component.css'
})
export class CriminalCardComponent {
  criminal = input.required<Criminal>();

  private router = inject(Router);

  showModalitiesForCriminal(criminalId: string) {
    this.router.navigateByUrl(`criminals/${criminalId}/modalities`);
  }
}
