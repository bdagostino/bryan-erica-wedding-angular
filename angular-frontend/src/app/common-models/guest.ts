import {Food} from './food';
import {Invitation} from './invitation';

export class Guest {

  constructor(public id: number,
              public firstName: string,
              public lastName: string,
              public ceremonyAttendance: boolean,
              public receptionAttendance: boolean,
              public food: Food,
              public dietaryConcerns: boolean,
              public dietaryComments: string,
              public plannedGuest: boolean,
              public invitation: Invitation) {
  }
}
