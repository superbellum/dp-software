import {NotificationType} from './notification-type';
import {NotificationHeader} from './notification-header.model';

export interface Notification {
  id: string;
  header: NotificationHeader | null;
  text: string;
  dismissTimeout: number | null;
  type: NotificationType
}
