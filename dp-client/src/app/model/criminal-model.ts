import {Address} from './address';

export interface CriminalModel {
  id: string,
  firstName: string,
  lastName: string,
  phoneNumber: string | null,
  email: string | null,
  address: Address
}
