import {Address} from '../../../address';

export interface CriminalRequestDto {
  firstName: string
  lastName: string
  phoneNumber: string | null
  email: string | null
  address: Address
}
