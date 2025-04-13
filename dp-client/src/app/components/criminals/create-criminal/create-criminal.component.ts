import {Component, inject, signal} from '@angular/core';
import {CriminalService} from '../../../services/criminal.service';
import {FormsModule} from '@angular/forms';
import {Location} from '@angular/common';
import {CriminalResponseDto} from '../../../model/payload/response/dto/criminal-response-dto';
import {CriminalEnrollRequest} from '../../../model/payload/request/criminal-enroll-request';
import {NotificationService} from '../../../services/notification.service';

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
  private notificationService = inject(NotificationService);
  private location = inject(Location);

  goBack() {
    this.location.back();
  }

  onSubmit() {
    const criminalEnrollRequest: CriminalEnrollRequest = {
      criminal: {
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
      },
      modalities: []
    };

    this.criminalService.enroll(criminalEnrollRequest).subscribe({
      next: (response) => {
        const criminalResponse = response as CriminalResponseDto
        this.notificationService.success("Enrolled Criminal", null, 3000);
        this.location.back();
      },
      error: (response) => {
        // todo: check return type
        console.log(response);
      }
    });
  }
}
