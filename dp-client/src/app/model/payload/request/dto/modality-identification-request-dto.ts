import {ModalityType} from '../../../modality-type';

export interface ModalityIdentificationRequestDto {
  type: ModalityType
  rawData: string
}
