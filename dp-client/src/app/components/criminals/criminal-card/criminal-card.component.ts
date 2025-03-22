import {Component, inject, input, output} from '@angular/core';
import {Criminal} from '../../../model/criminal.model';
import {Router} from '@angular/router';
import {CriminalService} from '../../../services/criminal.service';

@Component({
  selector: 'app-criminal-card',
  imports: [],
  templateUrl: './criminal-card.component.html',
  styleUrl: './criminal-card.component.css'
})
export class CriminalCardComponent {
  criminal = input.required<Criminal>();
  onDeleteCriminal = output<string>();

  private criminalService = inject(CriminalService);
  private router = inject(Router);

  showModalitiesForCriminal() {
    this.router.navigateByUrl(`criminals/${this.criminal().id}/modalities`);
  }

  deleteCriminalById() {
    this.criminalService.deleteCriminalById(this.criminal().id).subscribe({
      next: (res) => {
        console.log(res.message);
        this.onDeleteCriminal.emit(this.criminal().id);
      },
      error: (err) => console.error(err)
    });
  }
}
