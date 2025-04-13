import {Component, inject, input, signal} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CriminalService} from '../../../../../services/criminal.service';
import {ModalityType} from '../../../../../model/modality-type';
import {AddModalitiesRequest} from '../../../../../model/payload/request/add-modalities-request';
import {onFileSelected} from '../../../../../utils/onFileSelected';
import {IrisPosition} from '../../../../../model/iris-position';
import {IrisModalityRequestDto} from '../../../../../model/payload/request/dto/iris-modality-request-dto';
import {NotificationService} from '../../../../../services/notification.service';

@Component({
  selector: 'app-create-iris-modality-form',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './create-iris-modality-form.component.html',
  styleUrl: './create-iris-modality-form.component.css'
})
export class CreateIrisModalityFormComponent {
  criminalId = input.required<string | null>();
  modalityData = signal<string | null>(null);
  irisPositions = Object.values(IrisPosition) as IrisPosition[];
  selectedIrisPosition = signal<IrisPosition>(this.irisPositions[0]);

  private criminalService = inject(CriminalService);
  private notificationService = inject(NotificationService);

  onSubmit() {
    if (this.criminalId() && this.modalityData()) {
      const requestModality: IrisModalityRequestDto = {
        type: ModalityType.IRIS,
        rawData: this.modalityData()!,
        position: this.selectedIrisPosition()
      };

      const request: AddModalitiesRequest = {
        modalities: [requestModality]
      };

      this.criminalService.addModalitiesToCriminal(this.criminalId()!, request).subscribe({
        next: (res) => {
          this.notificationService.success(res.message, null, 3000);
          console.log(res);
        },
        error: (err) => console.error(err)
      });
    }
  }


  onIrisFileSelected(event: Event) {
    const onLoad = (result: string) => {
      this.modalityData.set(result.split(',')[1]);
    };
    onFileSelected(event, onLoad, (err) => console.log(err))
  }
}
