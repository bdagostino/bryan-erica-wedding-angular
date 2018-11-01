import {Component, OnInit} from '@angular/core';
import {Invitation} from '../../common-models/invitation';
import {Food} from '../../common-models/food';
import {Guest} from '../../common-models/guest';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {faWindowClose} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-rsvp-form',
  templateUrl: './rsvp-form.component.html',
  styleUrls: ['./rsvp-form.component.css']
})
export class RsvpFormComponent implements OnInit {
  faWindowClose = faWindowClose;
  foodList = [new Food(1, 'Chicken', 'Roasted Chicken'), new Food(2, 'Steak', 'Ribeye Steak')];
  guest1 = new Guest(1, 'Jack', 'Sparrow', true, true, this.foodList[1], false, '', true, null);
  guest2 = new Guest(1, 'Elizabeth', 'Swan', false, true, null, true, 'I hate peanuts', false, null);
  guest3 = new Guest(1, 'Bryan', 'D', false, true, null, false, 'I hate apples', true, null);

  invitation = new Invitation(1, [this.guest1, this.guest2, this.guest3], [this.guest2, this.guest1], 2, 'ABCD');


  rsvpModifyForm: FormGroup;

  constructor() {
  }

  ngOnInit() {
    this.rsvpModifyForm = new FormGroup({
      'invitedGuestList': new FormArray(this.populateInvitedGuestList()),
      'additionalGuestList': new FormArray(this.populateAdditionalGuestList())
    });
  }

  get invitedGuestListForms() {
    return this.rsvpModifyForm.get('invitedGuestList') as FormArray;
  }

  get additionalGuestListForms() {
    return this.rsvpModifyForm.get('additionalGuestList') as FormArray;
  }

  populateInvitedGuestList() {
    const invitationGuestList: FormGroup[] = [];
    this.invitation.invitedGuestList.forEach(guest => {
      invitationGuestList.push(this.createGuestFormControl(guest.firstName, guest.lastName, guest.ceremonyAttendance,
        guest.receptionAttendance, guest.food, guest.dietaryConcerns, guest.dietaryComments));
    });
    return invitationGuestList;
  }

  populateAdditionalGuestList() {
    const additionalGuestList: FormGroup[] = [];
    this.invitation.additionalGuestList.forEach(guest => {
      additionalGuestList.push(this.createGuestFormControl(guest.firstName, guest.lastName, guest.ceremonyAttendance,
        guest.receptionAttendance, guest.food, guest.dietaryConcerns, guest.dietaryComments));
    });
    return additionalGuestList;
  }

  onSubmit() {
    console.log(this.rsvpModifyForm.value);
  }

  addGuest() {
    const guest = this.createGuestFormControl('a', 'b', null, null, null, null, null);
    this.additionalGuestListForms.push(guest);
  }

  removeGuest(i) {
    this.additionalGuestListForms.removeAt(i);
  }

  createGuestFormControl(firstName: string, lastName: string, ceremonyAttendance: boolean,
                         receptionAttendance: boolean, food: Food, dietaryConcerns: boolean, dietaryComments: string) {
    return new FormGroup({
      'firstName': new FormControl(firstName, [Validators.required]),
      'lastName': new FormControl(lastName, [Validators.required]),
      'ceremonyAttendance': new FormControl(ceremonyAttendance, [Validators.required]),
      'receptionAttendance': new FormControl(receptionAttendance, [Validators.required]),
      'food': new FormControl(food != null ? food.id.toString() : null, [Validators.required]),
      'dietaryConcerns': new FormControl(dietaryConcerns, [Validators.required]),
      // TODO: Custom Validations needed
      'dietaryComments': new FormControl(dietaryComments, [])
    });
  }
}
