import {Component, inject, signal} from '@angular/core';
import {StorageService} from '../../services/storage.service';
import {Router, RouterLink} from '@angular/router';
import {ThemeSwitchComponent} from './theme-switch/theme-switch.component';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, ThemeSwitchComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  activeNavTab = signal("");

  private storageService = inject(StorageService);
  private router = inject(Router);

  constructor() {
    const storedActiveNavTab: string = this.storageService.retrieveActiveNavTab();

    if (storedActiveNavTab) {
      this.activeNavTab.set(storedActiveNavTab);
    } else {
      this.setActiveNavTab("criminals");
      this.router.navigateByUrl("/");
    }
  }

  setActiveNavTab(activeNavTab: string) {
    this.storageService.storeActiveNavTab(activeNavTab);
    this.activeNavTab.set(activeNavTab);
  }
}
