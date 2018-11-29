import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {log} from 'util';
import {Food} from '../../common-models/food';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  constructor(private http: HttpClient) {
  }

  saveFood(request: Food): Observable<void> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'my-auth-token'
      })
    };
    log(environment.host + '/admin/save-food');
    return this.http.post<void>(environment.host + '/admin/save-food', request, httpOptions);
  }
}
