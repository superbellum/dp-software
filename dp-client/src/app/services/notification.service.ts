import {Injectable, signal} from '@angular/core';
import {Notification} from '../model/notification.model';
import {NotificationType} from '../model/notification-type';
import {NotificationHeader} from '../model/notification-header.model';

@Injectable({providedIn: 'root'})
export class NotificationService {
  notifications = signal<Notification[]>([]);

  info(text: string, header: NotificationHeader | null = null, dismissTimeout: number | null = null) {
    this.notify(NotificationType.INFO, text, header, dismissTimeout);
  }

  success(text: string, header: NotificationHeader | null = null, dismissTimeout: number | null = null) {
    this.notify(NotificationType.SUCCESS, text, header, dismissTimeout);
  }

  warning(text: string, header: NotificationHeader | null = null, dismissTimeout: number | null = null) {
    this.notify(NotificationType.WARNING, text, header, dismissTimeout);
  }

  error(text: string, header: NotificationHeader | null = null, dismissTimeout: number | null = null) {
    this.notify(NotificationType.ERROR, text, header, dismissTimeout);
  }

  notify(type: NotificationType, text: string, header: NotificationHeader | null = null, dismissTimeout: number | null = null) {
    const notification: Notification = {
      id: `notification-${Math.random()}`,
      header: header,
      text: text,
      dismissTimeout: dismissTimeout,
      type: type
    };

    this.notifications.set([notification, ...this.notifications()]);

    if (dismissTimeout) {
      setTimeout(() => {
        this.notifications.set(this.notifications().filter(n => n.id !== notification.id));
      }, notification.dismissTimeout!);
    }
  }

  dismissNotification(id: string) {
    this.notifications.set(this.notifications().filter(n => n.id !== id));
  }
}
