import {Component, inject} from '@angular/core';
import {Router} from '@angular/router';
import {CriminalService} from '../../../services/criminal.service';

@Component({
  selector: 'app-create-criminal',
  imports: [],
  templateUrl: './create-criminal.component.html',
  styleUrl: './create-criminal.component.css'
})
export class CreateCriminalComponent {
  private criminalService = inject(CriminalService);
  private router = inject(Router);


  goBack() {
    this.router.navigate(['..']);
  }

}
