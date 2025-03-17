import {Modality} from '../modality.model';

export interface GetModalitiesForCriminalResponse {
  criminalId: string;
  modalities: Modality[]
}
