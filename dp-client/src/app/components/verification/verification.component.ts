import {Component, inject, signal} from '@angular/core';
import {BiometricService} from '../../services/biometric.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {VerificationRequest} from '../../model/payload/verification-request.model';
import {NgStyle} from '@angular/common';

@Component({
  selector: 'app-verification',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgStyle
  ],
  templateUrl: './verification.component.html',
  styleUrl: './verification.component.css'
})
export class VerificationComponent {
  // form inputs
  matchScoreThreshold = signal<number>(80);
  criminalId = signal<string | null>(null);
  modalityType = signal<string | null>(null);
  modalityData = signal<string | null>(null);
  verified = signal<boolean | null>(null);
  matchScore = signal<number | null>(null);

  private biometricService = inject(BiometricService);

  onSubmit() {
    if (this.criminalId() && this.modalityType() && this.modalityData()) {
      const verificationRequest: VerificationRequest = {
        criminalId: this.criminalId()!,
        modality: {
          type: this.modalityType()!,
          data: this.modalityData()!
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

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) {
      return;
    }

    const file = input.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.modalityData.set((reader.result as string).split(',')[1]);
    };
    reader.onerror = (error) => {
      console.error('Error reading file:', error);
    };
  }
}
