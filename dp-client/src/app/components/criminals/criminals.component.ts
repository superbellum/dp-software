import {Component, inject, OnInit, signal} from '@angular/core';
import {CriminalService} from '../../services/criminal.service';
import {CriminalCardComponent} from './criminal-card/criminal-card.component';
import {Router} from '@angular/router';
import {catchError, map, of} from 'rxjs';
import {GetAllCriminalsResponse} from '../../model/payload/response/get-all-criminals-response';
import {CriminalModel} from '../../model/criminal-model';
import {NotificationService} from '../../services/notification.service';
import {TooltipPosition} from '../../model/tooltip-position';
import {TooltipDirective} from '../../directives/tooltip.directive';

@Component({
  selector: 'app-criminals',
  imports: [
    CriminalCardComponent,
    TooltipDirective,
  ],
  templateUrl: './criminals.component.html',
  styleUrl: './criminals.component.css'
})
export class CriminalsComponent implements OnInit {
  criminals = signal<CriminalModel[] | undefined>(undefined);
  loading = signal(true);

  private criminalService = inject(CriminalService);
  protected notificationService = inject(NotificationService);
  private router = inject(Router);

  ngOnInit(): void {
    this.fetchCriminals();
  }

  protected fetchCriminals() {
    this.loading.set(true);
    this.criminalService
      .getAllCriminals()
      .pipe(
        map(response => this.convertResponseToCriminalModels(response)),
        catchError(error => {
          console.error('Failed to fetch criminals:', error);
          this.notificationService.error(error.statusText, null, 2000);
          this.loading.set(false);
          return of([]);
        })
      )
      .subscribe(criminalModels => {
        this.criminals.set(criminalModels);
        this.loading.set(false);
      });
  }

  createCriminal() {
    this.router.navigateByUrl("create-criminal");
  }

  onDeletedCriminal(criminalId: string) {
    this.criminals.set(this.criminals()?.filter(c => c.id !== criminalId));
  }

  private convertResponseToCriminalModels(response: GetAllCriminalsResponse): CriminalModel[] {
    return response.criminals.map(criminalResponseDto => {
      return {
        id: criminalResponseDto.id,
        email: criminalResponseDto.email,
        address: criminalResponseDto.address,
        firstName: criminalResponseDto.firstName,
        lastName: criminalResponseDto.lastName,
        phoneNumber: criminalResponseDto.phoneNumber
      };
    })
  }

  protected readonly TooltipPosition = TooltipPosition;
}
