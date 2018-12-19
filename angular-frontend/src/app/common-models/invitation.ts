import {Guest} from './guest';
import {max} from "rxjs/operators";

export class Invitation {

    constructor(public maxGuests: number,
              public guestList: Array<Guest>,
              public id?: number,
              public invitationCode?: string) {
  }

  // getOriginalGuests(): Array<Guest> {
  //   return this.guestList.filter(guest => guest.additionalGuest == false);
  // }
  //
  // getAdditionalGuests(): Array<Guest> {
  //   return this.guestList.filter(guest => guest.additionalGuest == true);
  // }

  static getOriginalGuestsString(guestList: Array<Guest>): string {
    let guestListFiltered = guestList.filter(guest => guest.additionalGuest == false);
    let text = '';
    guestListFiltered.forEach(guest => {
      text += guest.firstName + ' ' + guest.lastName + '[' + guest.id + '] \n';
    });
    return text;
  }
}

