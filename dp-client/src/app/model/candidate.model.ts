import {Criminal} from './criminal.model';

export interface Candidate {
  criminal: Criminal;
  matchScore: number;
}
