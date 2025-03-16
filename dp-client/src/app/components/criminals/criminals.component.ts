import {Component, inject, OnInit, signal} from '@angular/core';
import {CriminalService} from '../../services/criminal.service';
import {Criminal} from '../../model/criminal.model';
import {CriminalCardComponent} from './criminal-card/criminal-card.component';

@Component({
  selector: 'app-criminals',
  imports: [
    CriminalCardComponent
  ],
  templateUrl: './criminals.component.html',
  styleUrl: './criminals.component.css'
})
export class CriminalsComponent implements OnInit {
  criminals = signal<Criminal[] | undefined>(undefined)

  private criminalService = inject(CriminalService);

  ngOnInit(): void {
    this.criminalService.getAllCriminals().subscribe(data => this.criminals.set(data.criminals));
  }
}
