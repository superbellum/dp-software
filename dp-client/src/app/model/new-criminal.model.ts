import {Address} from './address.model';

export interface NewCriminal {
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  address: Address
}
