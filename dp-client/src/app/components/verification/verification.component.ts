import {Component, inject, signal} from '@angular/core';
import {BiometricService} from '../../services/biometric.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {VerificationRequest} from '../../model/payload/request/verification-request';
import {ModalityType} from '../../model/modality-type';
import {onFileSelected} from '../../utils/onFileSelected';

@Component({
  selector: 'app-verification',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './verification.component.html',
  styleUrl: './verification.component.css'
})
export class VerificationComponent {
  // form inputs
  matchScoreThreshold = signal<number>(80);
  criminalId = signal<string | null>(null);
  modalityType = signal<ModalityType | null>(null);
  modalityRawData = signal<string | null>(null);
  verified = signal<boolean | null>(null);
  matchScore = signal<number | null>(null);

  private biometricService = inject(BiometricService);

  onSubmit() {
    if (this.criminalId() && this.modalityType() && this.modalityRawData()) {
      const verificationRequest: VerificationRequest = {
        criminalId: this.criminalId()!,
        modality: {
          type: this.modalityType()!,
          rawData: this.modalityRawData()!
        },
        verificationParameters: {
          matchScoreThreshold: this.matchScoreThreshold(),
        }
      }

      this.biometricService.verify(verificationRequest).subscribe({
        next: (res) => {
          this.verified.set(res.verified);
          this.matchScore.set(res.matchScore);
        },
        error: (err) => console.error(err)
      });
    }
  }

  onModalityFileSelected(event: Event) {
    const onLoad = (result: string) => {
      this.modalityRawData.set(result.split(',')[1]);
    }
    onFileSelected(event, onLoad, (err) => console.error('Error reading file:', err));
  }

  protected readonly ModalityType = ModalityType;
}
