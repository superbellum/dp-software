import {Component, inject, input, output} from '@angular/core';
import {Modality} from '../../../../model/modality.model';
import {DomSanitizer} from '@angular/platform-browser';
import {CriminalService} from '../../../../services/criminal.service';

@Component({
  selector: 'app-modality-card',
  imports: [],
  templateUrl: './modality-card.component.html',
  styleUrl: './modality-card.component.css'
})
export class ModalityCardComponent {
  modality = input.required<Modality>();
  onDeleteModality = output<string>();

  private criminalService = inject(CriminalService);

  private sanitizer = inject(DomSanitizer);

  get modalityImage() {
    return this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${this.modality().data}`);
  }

  deleteModalityById() {
    this.criminalService.deleteModalityForCriminal(this.modality().criminalId, this.modality().id).subscribe({
      next: res => {
        console.log(res.message);
        this.onDeleteModality.emit(this.modality().id);
      },
      error: err => console.log(err)
    });
  }
}
