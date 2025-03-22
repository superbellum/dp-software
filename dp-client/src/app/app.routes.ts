import {Routes} from '@angular/router';
import {CriminalsComponent} from './components/criminals/criminals.component';
import {IdentificationComponent} from './components/identification/identification.component';
import {VerificationComponent} from './components/verification/verification.component';
import {ModalitiesComponent} from './components/criminals/modalities/modalities.component';
import {CreateCriminalComponent} from './components/criminals/create-criminal/create-criminal.component';
import {CreateModalityComponent} from './components/criminals/modalities/create-modality/create-modality.component';

export const routes: Routes = [
  {
    path: '',
    component: CriminalsComponent
  },
  {
    path: 'new-criminal',
    component: CreateCriminalComponent
  },
  {
    path: 'criminals/:id/modalities',
    component: ModalitiesComponent
  },
  {
    path: 'criminals/:id/modalities/new-modality',
    component: CreateModalityComponent
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
