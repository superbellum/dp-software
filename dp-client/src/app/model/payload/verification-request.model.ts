import {VerificationParameters} from './verification-parameters.model';

export interface VerificationRequest {
  criminalId: string;
  modality: {
    data: string;
    type: string;
  };
  verificationParameters: VerificationParameters
}
