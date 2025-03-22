import {Component, inject, signal} from '@angular/core';
import {Router} from '@angular/router';
import {CriminalService} from '../../../services/criminal.service';
import {FormsModule} from '@angular/forms';
import {NewCriminal} from '../../../model/new-criminal.model';

@Component({
  selector: 'app-create-criminal',
  imports: [
    FormsModule
  ],
  templateUrl: './create-criminal.component.html',
  styleUrl: './create-criminal.component.css'
})
export class CreateCriminalComponent {
  country = signal('');
  city = signal('');
  street = signal('');
  streetNumber = signal('');
  postCode = signal('');
  email = signal('');
  firstName = signal('');
  lastName = signal('');
  phoneNumber = signal('');

  private criminalService = inject(CriminalService);
  private router = inject(Router);

  goBack() {
    this.router.navigate(['..']);
  }

  onSubmit() {
    const newCriminal: NewCriminal = {
      address: {
        city: this.city(),
        country: this.country(),
        postCode: this.postCode(),
        street: this.street(),
        streetNumber: this.streetNumber()
      },
      email: this.email(),
      firstName: this.firstName(),
      lastName: this.lastName(),
      phoneNumber: this.phoneNumber()
    };
    this.criminalService.createCriminal(newCriminal).subscribe({
      next: (resp) => {
        console.log(resp);
        this.router.navigate([".."]);
      },
      error: (err) => console.error(err)
    });
  }

}
