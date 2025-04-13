import {CriminalResponseDto} from './payload/response/dto/criminal-response-dto';

export interface CriminalCandidate {
  criminal: CriminalResponseDto
  matchScore: number
}
