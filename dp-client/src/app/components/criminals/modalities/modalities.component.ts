import {Component, inject, OnInit, signal} from '@angular/core';
import {Modality} from '../../../model/modality.model';
import {CriminalService} from '../../../services/criminal.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ModalityCardComponent} from './modality-card/modality-card.component';
import {Location} from '@angular/common';

@Component({
  selector: 'app-modalities',
  imports: [
    ModalityCardComponent
  ],
  templateUrl: './modalities.component.html',
  styleUrl: './modalities.component.css'
})
export class ModalitiesComponent implements OnInit {
  modalities = signal<Modality[] | undefined>(undefined);
  criminalId = signal("");

  private criminalService = inject(CriminalService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private location = inject(Location);

  ngOnInit(): void {
    const criminalId = this.route.snapshot.paramMap.get('id');

    if (criminalId) {
      this.criminalId.set(criminalId);
      this.criminalService
        .getModalitiesForCriminal(criminalId)
        .subscribe(data => {
          this.modalities.set(data.modalities);
        });
    }
  }

  goBack() {
    this.location.back();
  }

  deleteAllModalitiesForCriminal() {
    this.criminalService.deleteAllModalitiesForCriminal(this.criminalId()).subscribe({
      next: (res) => {
        console.log(res.message);
        this.modalities.set([]);
      },
      error: (err) => console.error(err)
    });
  }

  addNewModalityToCriminal() {
    this.router.navigateByUrl(`criminals/${this.criminalId()}/modalities/new-modality`);
  }

  onDeletedModality(modalityId: string) {
    this.modalities.set(this.modalities()?.filter(m => m.id !== modalityId));
  }
}
