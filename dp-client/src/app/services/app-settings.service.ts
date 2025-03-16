import {inject, Injectable, signal} from '@angular/core';
import {StorageService} from './storage.service';

@Injectable({providedIn: 'root'})
export class AppSettingsService {
  private storageService = inject(StorageService);
}
