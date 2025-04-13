import {ModalityType} from '../../../modality-type';

export interface ModalityVerificationRequestDto {
  type: ModalityType
  rawData: string
}
