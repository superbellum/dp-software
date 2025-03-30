import {Component, inject, OnInit, signal} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CriminalService} from '../../../../services/criminal.service';
import {ActivatedRoute} from '@angular/router';
import {AddModality} from '../../../../model/add-modality.model';
import {Location} from '@angular/common';

@Component({
  selector: 'app-create-modality',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './create-modality.component.html',
  styleUrl: './create-modality.component.css'
})
export class CreateModalityComponent implements OnInit {
  criminalId = signal<string | null>(null);
  modalityType = signal<string | null>(null);
  modalityData = signal<string | null>(null);

  private criminalService = inject(CriminalService);
  private route = inject(ActivatedRoute);
  private location = inject(Location);

  ngOnInit(): void {
    const criminalId = this.route.snapshot.paramMap.get('id');
    this.criminalId.set(criminalId);
  }

  goBack() {
    this.location.back();
  }

  onSubmit() {
    if (this.criminalId() && this.modalityType() && this.modalityData()) {
      const addModality: AddModality = {
        type: this.modalityType()!,
        data: this.modalityData()!
      }

      this.criminalService.addModalitiesToCriminal(this.criminalId()!, {modalities: [addModality]}).subscribe({
        next: (res) => {
          console.log(res);
          this.location.back();
        },
        error: (err) => console.error(err)
      });
    }
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) {
      return;
    }

    const file = input.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.modalityData.set((reader.result as string).split(',')[1]);
    };
    reader.onerror = (error) => {
      console.error('Error reading file:', error);
    };
  }
}
