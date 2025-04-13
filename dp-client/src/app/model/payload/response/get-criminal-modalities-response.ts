import {ModalityResponseDtoType} from './modality-response-dto-type';

export interface GetCriminalModalitiesResponse {
  criminalId: string
  modalities: ModalityResponseDtoType[] | null
}
