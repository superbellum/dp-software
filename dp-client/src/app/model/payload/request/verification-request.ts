import {VerificationParameters} from '../../verification-parameters';
import {ModalityVerificationRequestDto} from './dto/modality-verification-request-dto';

export interface VerificationRequest {
  criminalId: string;
  modality: ModalityVerificationRequestDto;
  verificationParameters: VerificationParameters | null;
}
