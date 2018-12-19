import {Food} from './food';

export class Guest {

  constructor(public id: number,
              public firstName: string,
              public lastName: string,
              public ceremonyAttendance: boolean,
              public receptionAttendance: boolean,
              public food: Food,
              public dietaryConcerns: boolean,
              public dietaryComments: string,
              public additionalGuest: boolean) {
  }
}
