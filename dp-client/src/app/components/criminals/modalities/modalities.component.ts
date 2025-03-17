import {Component, inject, OnInit, signal} from '@angular/core';
import {Modality} from '../../../model/modality.model';
import {CriminalService} from '../../../services/criminal.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ModalityCardComponent} from './modality-card/modality-card.component';

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

  ngOnInit(): void {
    const criminalId = this.route.snapshot.paramMap.get('id');

    if (criminalId) {
      this.criminalService
        .getModalitiesForCriminal(criminalId)
        .subscribe(data => {
          this.modalities.set(data.modalities);
          this.criminalId.set(data.criminalId);
        });
    }
  }
}
