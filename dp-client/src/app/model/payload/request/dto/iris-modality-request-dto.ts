import {ModalityRequestDto} from './modality-request-dto';
import {IrisPosition} from '../../../iris-position';

export interface IrisModalityRequestDto extends ModalityRequestDto {
  position: IrisPosition
}
