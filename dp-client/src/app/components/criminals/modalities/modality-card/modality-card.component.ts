import {Component, inject, input} from '@angular/core';
import {Modality} from '../../../../model/modality.model';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-modality-card',
  imports: [],
  templateUrl: './modality-card.component.html',
  styleUrl: './modality-card.component.css'
})
export class ModalityCardComponent {
  modality = input.required<Modality>();

  private sanitizer = inject(DomSanitizer);

  get modalityImage() {
    return this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${this.modality().data}`);
  }

  deleteModalityById(modalityId: string) {
    // todo
  }
}
