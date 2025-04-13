import {IdentificationParameters} from '../../identification-parameters';
import {ModalityIdentificationRequestDto} from './dto/modality-identification-request-dto';

export interface IdentificationRequest {
  modality: ModalityIdentificationRequestDto;
  identificationParameters: IdentificationParameters | null;
}
