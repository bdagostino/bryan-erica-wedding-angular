import {Component, OnInit} from '@angular/core';
import {InvitationService} from "../../../services/invitation.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-create-invitation',
  templateUrl: './create-invitation.component.html',
  styleUrls: ['./create-invitation.component.css']
})
export class CreateInvitationComponent implements OnInit {

  createInvitationForm: FormGroup;

  constructor(private invitationService: InvitationService, private router: Router) {
  }

  ngOnInit() {
    this.createInvitationForm = new FormGroup({
      'maxGuests': new FormControl()
    });
  }

}
