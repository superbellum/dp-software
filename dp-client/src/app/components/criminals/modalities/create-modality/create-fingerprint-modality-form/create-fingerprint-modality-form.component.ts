import {Component, inject, input, signal} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CriminalService} from '../../../../../services/criminal.service';
import {FingerPosition} from '../../../../../model/finger-position';
import {onFileSelected} from '../../../../../utils/onFileSelected';
import {AddModalitiesRequest} from '../../../../../model/payload/request/add-modalities-request';
import {ModalityType} from '../../../../../model/modality-type';
import {FingerprintModalityRequestDto} from '../../../../../model/payload/request/dto/fingerprint-modality-request-dto';
import {NotificationService} from '../../../../../services/notification.service';

@Component({
  selector: 'app-create-fingerprint-modality-form',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './create-fingerprint-modality-form.component.html',
  styleUrl: './create-fingerprint-modality-form.component.css'
})
export class CreateFingerprintModalityFormComponent {
  criminalId = input.required<string | null>();
  modalityData = signal<string | null>(null);
  fingerPositions = Object.values(FingerPosition) as FingerPosition[];
  selectedFingerprintPosition = signal<FingerPosition>(this.fingerPositions[0]);

  private criminalService = inject(CriminalService);
  private notificationService = inject(NotificationService);

  onSubmit() {
    if (this.criminalId() && this.modalityData()) {
      const requestModality: FingerprintModalityRequestDto = {
        type: ModalityType.FINGERPRINT,
        rawData: this.modalityData()!,
        position: this.selectedFingerprintPosition()
      };

      const request: AddModalitiesRequest = {
        modalities: [requestModality]
      };

      this.criminalService.addModalitiesToCriminal(this.criminalId()!, request).subscribe({
        next: (res) => {
          this.notificationService.success(res.message, null, 3000);
        },
        error: (err) => console.error(err)
      });
    }
  }


  onFingerprintFileSelected(event: Event) {
    const onLoad = (result: string) => {
      this.modalityData.set(result.split(',')[1]);
    };
    onFileSelected(event, onLoad, (err) => console.log(err))
  }
}
