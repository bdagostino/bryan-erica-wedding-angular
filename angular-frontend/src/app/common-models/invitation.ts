import {Guest} from './guest';

export class Invitation {
  constructor(public id: number,
              public invitedGuestList: Array<Guest>,
              public additionalGuestList: Array<Guest>,
              public maxAdditionalGuests: number,
              public invitationCode: string) {
  }
}
