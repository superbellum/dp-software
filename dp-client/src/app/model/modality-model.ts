import {ModalityType} from './modality-type';

export interface ModalityModel {
  id: string
  criminalId: string
  type: ModalityType
  rawData: string
}
