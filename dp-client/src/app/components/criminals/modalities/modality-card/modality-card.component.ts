import {Component, inject, input, output} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {CriminalService} from '../../../../services/criminal.service';
import {ModalityModel} from '../../../../model/modality-model';
import {NotificationService} from '../../../../services/notification.service';

@Component({
  selector: 'app-modality-card',
  imports: [],
  templateUrl: './modality-card.component.html',
  styleUrl: './modality-card.component.css'
})
export class ModalityCardComponent {
  modality = input.required<ModalityModel>();
  onDeleteModality = output<string>();

  private criminalService = inject(CriminalService);
  private notificationService = inject(NotificationService);
  private sanitizer = inject(DomSanitizer);

  get modalityImage() {
    return this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${this.modality().rawData}`);
  }

  removeModalityOfCriminal() {
    this.criminalService.removeModalityOfCriminal(this.modality().criminalId, this.modality().id).subscribe({
      next: res => {
        this.notificationService.success(res.message, null, 3000);
        console.log(res.message);
        this.onDeleteModality.emit(this.modality().id);
      },
      error: err => {
        // todo: notify
        console.log(err)
      }
    });
  }
}
