import {Component, OnInit} from '@angular/core';
import {Food} from '../../common-models/food';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {faWindowClose} from '@fortawesome/free-solid-svg-icons';
import {ActivatedRoute} from "@angular/router";
import {RsvpService} from "../rsvp.service";
import {log} from "util";
import {InvitationService} from "../../services/invitation.service";
import {Guest} from "../../common-models/guest";
import {Invitation} from "../../common-models/invitation";
import {Observable} from "rxjs";
import {FoodService} from "../../services/food.service";

@Component({
  selector: 'app-rsvp-form',
  templateUrl: './rsvp-form.component.html',
  styleUrls: ['./rsvp-form.component.css']
})
export class RsvpFormComponent implements OnInit {
  faWindowClose = faWindowClose;
  //foodList = [new Food(1, 'Chicken', 'Roasted Chicken'), new Food(2, 'Steak', 'Ribeye Steak')];
  // guest1 = new Guest(1, 'Jack', 'Sparrow', true, true, this.foodList[1], false, '', true, null);
  // guest2 = new Guest(1, 'Elizabeth', 'Swan', false, true, null, true, 'I hate peanuts', false, null);
  // guest3 = new Guest(1, 'Bryan', 'D', false, true, null, false, 'I hate apples', true, null);
  //
  // invitation = new Invitation(1, [this.guest1, this.guest2, this.guest3], [], 2, 'ABCD');

  loaded: boolean;
  rsvpModifyForm: FormGroup;
  invitedGuestList: Array<Guest>;
  additionalGuestList: Array<Guest>;
  maxAdditionalGuests: number;
  invitedGuestCount: number;
  foodList: Array<Food>;

  constructor(private rsvpService: RsvpService, private route: ActivatedRoute, private invitationService: InvitationService, private foodService: FoodService) {
  }

  ngOnInit() {
  this.loaded = false;
  this.invitedGuestCount = 0;

    this.route.queryParamMap.subscribe(paramMap => {
      this.invitationService.getInvitationsByInvitationCode(paramMap.get('invitationCode')).subscribe(retrievedInvitation => {
         this.rsvpModifyForm = new FormGroup({
          'invitedGuestList': new FormArray(this.populateInvitedGuestList(retrievedInvitation.guestList)),
          'additionalGuestList': new FormArray(this.populateAdditionalGuestList(retrievedInvitation.guestList))
        });
         this.maxAdditionalGuests = retrievedInvitation.maxGuests - this.invitedGuestCount;
        this.loaded = true;
      });
    });

    this.foodService.getAllFood().subscribe(retrievedFoodList => {
      this.foodList = retrievedFoodList;
    });
  }

  get invitedGuestListForms() {
    return this.rsvpModifyForm.get('invitedGuestList') as FormArray;
  }

  get additionalGuestListForms() {
    return this.rsvpModifyForm.get('additionalGuestList') as FormArray;
  }

  populateInvitedGuestList(guestList: Array<Guest>) {
    const invitationGuestList: FormGroup[] = [];
    guestList.filter(guest => guest.additionalGuest==false).forEach(guest => {
      this.invitedGuestCount += 1;
      invitationGuestList.push(this.createGuestFormControl(guest.firstName, guest.lastName, guest.ceremonyAttendance,
        guest.receptionAttendance, guest.food, guest.dietaryConcerns, guest.dietaryComments));
    });
    return invitationGuestList;
  }

  populateAdditionalGuestList(guestList: Array<Guest>) {
    const additionalGuestList: FormGroup[] = [];
    guestList.filter(guest => guest.additionalGuest==true).forEach(guest => {
      additionalGuestList.push(this.createGuestFormControl(guest.firstName, guest.lastName, guest.ceremonyAttendance,
        guest.receptionAttendance, guest.food, guest.dietaryConcerns, guest.dietaryComments));
    });
    return additionalGuestList;
  }

  onSubmitForm() {
    if (this.rsvpModifyForm.valid) {
      console.log('Form Valid');

    } else {
      console.log('Form Invalid');
      // this.rsvpModifyForm.markAsTouched();
      this.validateFields(this.rsvpModifyForm);
      // this.rsvpModifyForm.markAsTouched();
      // Object.keys(this.rsvpModifyForm.controls).forEach(field => {
      //   const control = this.rsvpModifyForm.get(field);
      //   control.markAsTouched({onlySelf: true});
      // });
    }

  }

  validateFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({onlySelf: true});
      } else if (control instanceof FormGroup) {
        this.validateFields(control);
      } else if (control instanceof FormArray) {
        control.controls.forEach(inner => {
          if (inner instanceof FormGroup) {
            this.validateFields(inner);
          }
        });
      }
    });
  }

  addGuest() {
    const guest = this.createGuestFormControl('', '', null, null, null, null, null);
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
