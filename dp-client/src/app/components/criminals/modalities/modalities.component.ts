import {Component, inject, OnInit, signal} from '@angular/core';
import {CriminalService} from '../../../services/criminal.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ModalityCardComponent} from './modality-card/modality-card.component';
import {Location} from '@angular/common';
import {IrisModalityResponseDto} from '../../../model/payload/response/dto/iris-modality-response-dto';
import {FingerprintModalityResponseDto} from '../../../model/payload/response/dto/fingerprint-modality-response-dto';
import {IrisModalityModel} from '../../../model/iris-modality-model';
import {FingerprintModalityModel} from '../../../model/fingerprint-modality-model';
import {ModalityType} from '../../../model/modality-type';
import {ModalityResponseDtoType} from '../../../model/payload/response/modality-response-dto-type';
import {ModalityModel} from '../../../model/modality-model';
import {GetCriminalModalitiesResponse} from '../../../model/payload/response/get-criminal-modalities-response';
import {NotificationService} from '../../../services/notification.service';
import {TooltipDirective} from '../../../directives/tooltip.directive';
import {TooltipPosition} from '../../../model/tooltip-position';

@Component({
  selector: 'app-modalities',
  imports: [
    ModalityCardComponent,
    TooltipDirective
  ],
  templateUrl: './modalities.component.html',
  styleUrl: './modalities.component.css'
})
export class ModalitiesComponent implements OnInit {
  modalities = signal<ModalityModel[] | undefined>(undefined);
  criminalId = signal("");
  loading = signal(true);

  private criminalService = inject(CriminalService);
  private notificationService = inject(NotificationService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private location = inject(Location);

  ngOnInit(): void {
    const criminalId = this.route.snapshot.paramMap.get('id');

    if (criminalId) {
      this.criminalId.set(criminalId);
    }

    this.fetchCriminalModalities();
  }

  protected fetchCriminalModalities() {
    if (this.criminalId()) {
      this.loading.set(true);
      this.criminalService
        .getModalitiesForCriminal(this.criminalId())
        .subscribe({
          next: (response) => {
            const getCriminalModalitiesResponse = response as GetCriminalModalitiesResponse;
            const modalityModels = this.convertResponseModalitiesToModels(getCriminalModalitiesResponse.modalities);
            this.modalities.set(modalityModels);
            this.loading.set(false);
          },
          error: (err) => {
            // todo: check return type
            console.error(`error in 'ModalitiesComponent:fetchCriminalModalities': ${err}`);
            this.loading.set(false);
          }
        });
    }
  }

  private convertResponseModalitiesToModels(responseModalities: ModalityResponseDtoType[] | null): ModalityModel[] | undefined {
    return responseModalities?.map(responseModality => {
      switch (responseModality.type) {
        case ModalityType.FINGERPRINT:
          return this.convertFingerprintResponseModalityToModel(responseModality as FingerprintModalityResponseDto);
        case ModalityType.IRIS:
          return this.convertIrisResponseModalityToModel(responseModality as IrisModalityResponseDto);
      }
    });
  }

  private convertFingerprintResponseModalityToModel(responseModality: FingerprintModalityResponseDto): FingerprintModalityModel {
    return {
      id: responseModality.id,
      criminalId: responseModality.criminalId,
      type: responseModality.type,
      rawData: responseModality.rawData,
      position: responseModality.position
    };
  }

  private convertIrisResponseModalityToModel(responseModality: IrisModalityResponseDto): IrisModalityModel {
    return {
      id: responseModality.id,
      criminalId: responseModality.criminalId,
      type: responseModality.type,
      rawData: responseModality.rawData,
      position: responseModality.position
    };
  }

  goBack() {
    this.location.back();
  }

  removeModalitiesOfCriminal() {
    this.criminalService.removeModalitiesOfCriminal(this.criminalId()).subscribe({
      next: (res) => {
        this.notificationService.success(res.message, null, 3000);
        console.log(res.message);
        this.modalities.set(undefined);
      },
      error: (err) => {
        // todo: notify
        console.error(err)
      }
    });
  }

  createModalityForCriminal() {
    this.router.navigateByUrl(`criminals/${this.criminalId()}/modalities/create-modality`);
  }

  onDeletedModality(modalityId: string) {
    this.modalities.set(this.modalities()?.filter(m => m.id !== modalityId));
  }

  protected readonly TooltipPosition = TooltipPosition;
}
