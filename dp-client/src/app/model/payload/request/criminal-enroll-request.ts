import {ModalityRequestDto} from './dto/modality-request-dto';
import {CriminalRequestDto} from './dto/criminal-request-dto';

export interface CriminalEnrollRequest {
  criminal: CriminalRequestDto;
  modalities: ModalityRequestDto[];
}

