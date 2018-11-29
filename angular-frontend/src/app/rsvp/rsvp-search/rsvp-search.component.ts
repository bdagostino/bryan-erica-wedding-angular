import {Component, OnInit} from '@angular/core';
import {RsvpSearch} from './rsvp-search';
import {log} from 'util';
import {RsvpService} from '../rsvp.service';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-rsvp-search',
  templateUrl: './rsvp-search.component.html',
  styleUrls: ['./rsvp-search.component.css']
})
export class RsvpSearchComponent implements OnInit {

  model = new RsvpSearch('', 'No Error');


  constructor(private rsvpService: RsvpService, private router: Router) {
  }

  rsvpSearchForm: FormGroup;
  errorMessage: string;
  private object: any;

  ngOnInit() {
    this.rsvpSearchForm = new FormGroup({
      invitationCode: new FormControl(null, [Validators.required])
    })
    ;
  }

  searchForInvitation() {
    if (this.rsvpSearchForm.valid) {
      log('Form Submitted');
      this.rsvpService.searchForInvitation(this.rsvpSearchForm).subscribe(next => {

        if (next.invitationExists) {
          this.router.navigate(['/rsvp/view'], {queryParams: {invitationCode: this.rsvpSearchForm.get('invitationCode').value}});
        } else {
          this.errorMessage = next.errorMessage;
        }

      }, error => {
        if (error.error instanceof ErrorEvent) {
          console.error('An error occurred:', error.error.message);
        } else {
          this.errorMessage = error.error.value;
        }
      });
    } else {
      this.rsvpSearchForm.get('invitationCode').markAsTouched();
    }
  }

}


