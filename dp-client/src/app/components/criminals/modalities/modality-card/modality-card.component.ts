import {Component, input} from '@angular/core';
import {Modality} from '../../../../model/modality.model';

@Component({
  selector: 'app-modality-card',
  imports: [],
  templateUrl: './modality-card.component.html',
  styleUrl: './modality-card.component.css'
})
export class ModalityCardComponent {
  modality = input.required<Modality>();
}
