import {Component, inject, signal} from '@angular/core';
import {BiometricService} from '../../services/biometric.service';
import {FormsModule} from '@angular/forms';
import {CandidateCardComponent} from './candidate-card/candidate-card.component';
import {IdentificationRequest} from '../../model/payload/request/identification-request';
import {ModalityType} from '../../model/modality-type';
import {CriminalCandidate} from '../../model/criminal-candidate';
import {onFileSelected} from '../../utils/onFileSelected';

@Component({
  selector: 'app-identification',
  imports: [
    FormsModule,
    CandidateCardComponent
  ],
  templateUrl: './identification.component.html',
  styleUrl: './identification.component.css'
})
export class IdentificationComponent {
  hitlist = signal<CriminalCandidate[] | null>(null)
  modalityType = signal<ModalityType | null>(null);
  modalityRawData = signal<string | null>(null);
  matchScoreThreshold = signal<number>(80);
  candidateCount = signal<number>(5);

  private biometricService = inject(BiometricService);

  onSubmit() {
    if (this.modalityType() && this.modalityRawData()) {
      const identificationRequest: IdentificationRequest = {
        identificationParameters: {
          matchScoreThreshold: this.matchScoreThreshold(),
          candidateCount: this.candidateCount()
        },
        modality: {
          type: this.modalityType()!,
          rawData: this.modalityRawData()!,
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

  onModalityFileSelected(event: Event) {
    const onLoad = (result: string) => {
      this.modalityRawData.set(result.split(',')[1]);
    }
    onFileSelected(event, onLoad, (err) => console.error('Error reading file:', err));
  }

  protected readonly ModalityType = ModalityType;
}
