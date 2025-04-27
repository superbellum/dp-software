import {Component} from '@angular/core';

@Component({
  selector: 'app-theme-switch',
  imports: [],
  templateUrl: './theme-switch.component.html',
  styleUrl: './theme-switch.component.css'
})
export class ThemeSwitchComponent {

  onThemeSwitchChanged(event: Event) {
    const isChecked = (<HTMLInputElement>event.target).checked;

    if (isChecked) {
      document.body.setAttribute('data-bs-theme', 'dark');
    } else {
      document.body.setAttribute('data-bs-theme', 'light');
    }
  }
}
