import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {Invitation} from '../common-models/invitation';

@Injectable({
  providedIn: 'root'
})
export class InvitationService {

  constructor(private http: HttpClient) {
  }

  getAllInvitations(): Observable<Invitation[]> {
    return this.http.get<Invitation[]>(environment.host + '/admin/invitations');
  }


  getInvitationById(id: number): Observable<Invitation> {
    return this.http.get<Invitation>(environment.host + '/admin/invitations/' + id);
  }

  createInvitation(invitation: Invitation) {
    return this.http.post(environment.host + '/admin/invitations', invitation);
  }

  updateInvitation(invitation: Invitation) {
    return this.http.put(environment.host + '/admin/invitations/' + invitation.id, invitation);
  }

  deleteInvitationById(id: number) {
    return this.http.delete(environment.host + '/admin/invitations/' + id);
  }

  getInvitationsByInvitationCode(invitationCode: string): Observable<Invitation> {
    return this.http.get<Invitation>(environment.host + '/invitations/' + invitationCode);
  }
}
