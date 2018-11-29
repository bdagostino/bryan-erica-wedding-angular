import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {FormGroup} from '@angular/forms';
import {RsvpSearchResponse} from './rsvp-search/interface/rsvp-search-response';

@Injectable({
  providedIn: 'root'
})
export class RsvpService {

  constructor(private http: HttpClient) {
  }

  searchForInvitation(rsvpSearchForm: FormGroup): Observable<RsvpSearchResponse> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'my-auth-token'
      })
    };
    return this.http.post<RsvpSearchResponse>(environment.host + '/rsvp/search', JSON.stringify(rsvpSearchForm.getRawValue()), httpOptions);
  }
}
