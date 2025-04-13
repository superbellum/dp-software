import {Component, inject, OnInit, signal} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {Location, NgClass} from '@angular/common';
import {ModalityType} from '../../../../model/modality-type';
import {
  CreateFingerprintModalityFormComponent
} from './create-fingerprint-modality-form/create-fingerprint-modality-form.component';
import {CreateIrisModalityFormComponent} from './create-iris-modality-form/create-iris-modality-form.component';
import {TooltipDirective} from '../../../../directives/tooltip.directive';

@Component({
  selector: 'app-create-modality',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CreateFingerprintModalityFormComponent,
    CreateIrisModalityFormComponent,
    NgClass,
    TooltipDirective
  ],
  templateUrl: './create-modality.component.html',
  styleUrl: './create-modality.component.css'
})

export class CreateModalityComponent implements OnInit {
  modalityToCreate = signal(ModalityType.FINGERPRINT);
  criminalId = signal<string | null>(null);
  readonly ModalityType = ModalityType;

  private route = inject(ActivatedRoute);
  private location = inject(Location);

  ngOnInit(): void {
    const criminalId = this.route.snapshot.paramMap.get('id');
    this.criminalId.set(criminalId);
  }

  goBack() {
    this.location.back();
  }
}
