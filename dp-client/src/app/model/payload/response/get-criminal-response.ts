import {CriminalResponseDto} from './dto/criminal-response-dto';
import {ModalityResponseDto} from './dto/modality-response-dto';

export interface GetCriminalResponse {
  criminal: CriminalResponseDto | null;
  modalities: ModalityResponseDto[];
}
