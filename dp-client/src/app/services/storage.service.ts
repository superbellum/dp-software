import {inject, Injectable} from '@angular/core';
import {LocalStorageService} from 'ngx-webstorage';

@Injectable({providedIn: 'root'})
export class StorageService {
  static readonly ACTIVE_NAV_TAB_KEY = 'activeNavTab';

  private localStorageService = inject(LocalStorageService);

  clear(): void {
    this.localStorageService.clear();
  }

  storeActiveNavTab(activeNavTab: string) {
    this.localStorageService.clear(StorageService.ACTIVE_NAV_TAB_KEY);
    this.localStorageService.store(StorageService.ACTIVE_NAV_TAB_KEY, activeNavTab);
  }

  retrieveActiveNavTab() {
    return this.localStorageService.retrieve(StorageService.ACTIVE_NAV_TAB_KEY);
  }
}
