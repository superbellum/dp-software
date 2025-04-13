import {Component, inject} from '@angular/core';
import {NotificationCardComponent} from "./notification-card/notification-card.component";
import {NotificationService} from '../../services/notification.service';
import {fadeInOut} from '../../animations/fade-in-out';

@Component({
  selector: 'app-notifications',
  imports: [
    NotificationCardComponent
  ],
  templateUrl: './notifications.component.html',
  styleUrl: './notifications.component.css',
  animations: [fadeInOut],
})
export class NotificationsComponent {
  protected notificationService = inject(NotificationService);
}
