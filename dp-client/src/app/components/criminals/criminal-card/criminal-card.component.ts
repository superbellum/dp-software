import {Component, inject, input, output, signal} from '@angular/core';
import {Router} from '@angular/router';
import {CriminalService} from '../../../services/criminal.service';
import {CriminalModel} from '../../../model/criminal-model';
import {NotificationService} from '../../../services/notification.service';
import {TooltipDirective} from '../../../directives/tooltip.directive';
import {TooltipPosition} from '../../../model/tooltip-position';

@Component({
  selector: 'app-criminal-card',
  imports: [
    TooltipDirective
  ],
  templateUrl: './criminal-card.component.html',
  styleUrl: './criminal-card.component.css'
})
export class CriminalCardComponent {
  criminal = input.required<CriminalModel>();
  tooltipCopyId = signal("Copy Criminal ID");
  onDeleteCriminal = output<string>();

  private criminalService = inject(CriminalService);
  private notificationService = inject(NotificationService);
  private router = inject(Router);

  showModalitiesForCriminal() {
    this.router.navigateByUrl(`criminals/${this.criminal().id}/modalities`);
  }

  deleteCriminalById() {
    this.criminalService.deleteCriminalById(this.criminal().id).subscribe({
      next: (res) => {
        this.notificationService.success(res.message, null, 3000);
        console.log(res);
        this.onDeleteCriminal.emit(this.criminal().id);
      },
      error: (err) => console.error(err)
    });
  }

  copyCriminalId() {
    navigator.clipboard.writeText(this.criminal().id)
      .then(() => this.tooltipCopyId.set("<i class='bi bi-check2-circle me-2'></i>Copied!"));
  }

  onMouseLeaveCopyId() {
    setTimeout(() => this.tooltipCopyId.set("Copy Criminal ID"), 100);
  }

  protected readonly TooltipPosition = TooltipPosition;
}
