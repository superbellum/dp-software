import {IdentificationParameters} from './identification-parameters.model';

export interface IdentificationRequest {
  identificationParameters: IdentificationParameters;
  modality: {
    type: string;
    data: string;
  };
}
