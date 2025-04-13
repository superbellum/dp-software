import {IrisPosition} from './iris-position';
import {ModalityModel} from './modality-model';

export interface IrisModalityModel extends ModalityModel {
  position: IrisPosition
}
