import {Address} from './address.model';

export interface Criminal {
  id: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  address: Address
}
