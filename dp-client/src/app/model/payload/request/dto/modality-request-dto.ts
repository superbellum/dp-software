import {ModalityType} from '../../../modality-type';

export interface ModalityRequestDto {
  type: ModalityType
  rawData: string
}
