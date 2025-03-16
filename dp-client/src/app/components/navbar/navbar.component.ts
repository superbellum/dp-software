import {Component, inject, signal} from '@angular/core';
import {StorageService} from '../../services/storage.service';

@Component({
  selector: 'app-navbar',
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  activeNavTab = signal("criminals");

  private storageService = inject(StorageService);

  constructor() {
    const storedActiveNavTab: string = this.storageService.retrieveActiveNavTab();
    this.activeNavTab.set(storedActiveNavTab ?? "criminals");
  }

  setActiveNavTab(activeNavTab: string) {
    this.storageService.storeActiveNavTab(activeNavTab);
    this.activeNavTab.set(activeNavTab);
  }
}
