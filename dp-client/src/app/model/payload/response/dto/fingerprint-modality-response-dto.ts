import {ModalityResponseDto} from './modality-response-dto';
import {FingerPosition} from '../../../finger-position';

export interface FingerprintModalityResponseDto extends ModalityResponseDto {
  position: FingerPosition
}
