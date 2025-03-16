import {Routes} from '@angular/router';
import {CriminalsComponent} from './components/criminals/criminals.component';
import {IdentificationComponent} from './components/identification/identification.component';
import {VerificationComponent} from './components/verification/verification.component';

export const routes: Routes = [
  {
    path: '',
    component: CriminalsComponent
  },
  {
    path: 'identification',
    component: IdentificationComponent
  },
  {
    path: 'verification',
    component: VerificationComponent
  }
];
