import {Address} from '../../../address';

export interface CriminalResponseDto {
  id: string,
  firstName: string,
  lastName: string,
  phoneNumber: string | null,
  email: string | null,
  address: Address
}
