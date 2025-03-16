import {Component, inject, signal} from '@angular/core';
import {StorageService} from '../../services/storage.service';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [
    RouterLink
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  activeNavTab = signal("");

  private storageService = inject(StorageService);

  constructor() {
    const storedActiveNavTab: string = this.storageService.retrieveActiveNavTab();

    if (storedActiveNavTab) {
      this.activeNavTab.set(storedActiveNavTab);
    } else {
      this.setActiveNavTab("criminals");
    }
  }

  setActiveNavTab(activeNavTab: string) {
    this.storageService.storeActiveNavTab(activeNavTab);
    this.activeNavTab.set(activeNavTab);
  }
}
