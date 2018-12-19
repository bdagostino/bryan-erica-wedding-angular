import {Component, OnInit} from '@angular/core';
import {InvitationService} from "../../services/invitation.service";
import {Router} from "@angular/router";
import {Invitation} from "../../common-models/invitation";
import {Guest} from "../../common-models/guest";

@Component({
  selector: 'app-invitation',
  templateUrl: './invitation.component.html',
  styleUrls: ['./invitation.component.css']
})
export class InvitationComponent implements OnInit {

  invitations: Array<Invitation>;

  constructor(private invitationService: InvitationService, private router: Router) {
  }

  ngOnInit() {
    this.invitationService.getAllInvitations().subscribe(response => {
      this.invitations = response;
    });


  }

  originalGuestFilter(guestList: Array<Guest>): Array<Guest> {
    return guestList.filter(guest => guest.additionalGuest == false);
  }

  additionalGuestFilter(guestList: Array<Guest>): Array<Guest> {
    return guestList.filter(guest => guest.additionalGuest == true);
  }
}
