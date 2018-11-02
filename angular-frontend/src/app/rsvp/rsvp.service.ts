import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {RsvpSearch} from './rsvp-search/rsvp-search';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RsvpService {

  constructor(private http: HttpClient) {
  }

  searchForInvitation(model: RsvpSearch): Observable<string> {
    return this.http.post<string>(environment.host + '/rsvp/submitForm', model);
  }
}
