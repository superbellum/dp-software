import {ModalityType} from '../../../modality-type';

export interface ModalityResponseDto {
  id: string
  criminalId: string
  type: ModalityType
  rawData: string
}
