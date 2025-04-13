import {ModalityResponseDto} from './modality-response-dto';
import {IrisPosition} from '../../../iris-position';

export interface IrisModalityResponseDto extends ModalityResponseDto {
  position: IrisPosition
}
