import {AfterViewInit, Component, inject, input} from '@angular/core';
import {Notification} from '../../../model/notification.model';
import {NgClass} from '@angular/common';
import {NotificationType} from '../../../model/notification-type';
import {NotificationService} from '../../../services/notification.service';

@Component({
  selector: 'app-notification-card',
  imports: [
    NgClass
  ],
  templateUrl: './notification-card.component.html',
  styleUrl: './notification-card.component.css'
})
export class NotificationCardComponent implements AfterViewInit {
  notification = input.required<Notification>();
  protected readonly NotificationType = NotificationType;

  private notificationService = inject(NotificationService);

  ngAfterViewInit(): void {
    // const toastLiveExample = document.getElementById(this.notification().id);
    // const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
  }

  get notifClass() {
    switch (this.notification().type) {
      case NotificationType.ERROR:
        return "text-bg-danger";
      case NotificationType.SUCCESS:
        return "text-bg-success";
      case NotificationType.WARNING:
        return "text-bg-warning";
      case NotificationType.INFO:
      default:
        return "text-bg-light";
    }
  }

  dismissNotification() {
    this.notificationService.dismissNotification(this.notification().id);
  }
}
