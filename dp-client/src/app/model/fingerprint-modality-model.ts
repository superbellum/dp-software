import {FingerPosition} from './finger-position';
import {ModalityModel} from './modality-model';

export interface FingerprintModalityModel extends ModalityModel {
  position: FingerPosition
}
