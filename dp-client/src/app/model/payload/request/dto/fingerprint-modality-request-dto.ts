import {ModalityRequestDto} from './modality-request-dto';
import {FingerPosition} from '../../../finger-position';

export interface FingerprintModalityRequestDto extends ModalityRequestDto {
  position: FingerPosition
}
