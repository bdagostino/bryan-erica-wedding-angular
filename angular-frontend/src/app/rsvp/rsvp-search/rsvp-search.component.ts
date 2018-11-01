import {Component, OnInit} from '@angular/core';
import {RsvpSearch} from './rsvp-search';
import {log} from 'util';
import {RsvpService} from '../rsvp.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-rsvp-search',
  templateUrl: './rsvp-search.component.html',
  styleUrls: ['./rsvp-search.component.css']
})
export class RsvpSearchComponent implements OnInit {

  model = new RsvpSearch('', 'No Error');

  get diagnostic() {
    return JSON.stringify(this.model);
  }

  constructor(private rsvpService: RsvpService, private router: Router) {
  }

  ngOnInit() {

  }

  onSubmit() {
    log('Form Submitted');
    this.rsvpService.searchForInvitation(this.model).subscribe(
      (value: string) => this.router.navigate(['/rsvp/modify']),
      log('Error in Search Component')
    );
  }

}
