import {Component, inject, signal} from '@angular/core';
import {BiometricService} from '../../services/biometric.service';
import {IdentificationRequest} from '../../model/payload/identification-request.model';
import {Candidate} from '../../model/candidate.model';
import {FormsModule} from '@angular/forms';
import {CriminalCardComponent} from '../criminals/criminal-card/criminal-card.component';
import {CandidateCardComponent} from './candidate-card/candidate-card.component';

@Component({
  selector: 'app-identification',
  imports: [
    FormsModule,
    CriminalCardComponent,
    CandidateCardComponent
  ],
  templateUrl: './identification.component.html',
  styleUrl: './identification.component.css'
})
export class IdentificationComponent {
  hitlist = signal<Candidate[] | null>(null)
  modalityType = signal<string | null>(null);
  modalityData = signal<string | null>(null);
  matchScoreThreshold = signal<number>(80);
  candidateCount = signal<number>(5);

  private biometricService = inject(BiometricService);

  onSubmit() {
    if (this.modalityType() && this.modalityData()) {
      const identificationRequest: IdentificationRequest = {
        identificationParameters: {
          matchScoreThreshold: this.matchScoreThreshold(),
          candidateCount: this.candidateCount()
        },
        modality: {
          type: this.modalityType()!,
          data: this.modalityData()!,
        }
      }

      this.biometricService.identify(identificationRequest).subscribe({
        next: (res) => {
          this.hitlist.set(res.hitlist);
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
