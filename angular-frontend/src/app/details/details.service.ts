import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {GoogleMapsDetails} from './interface/google-maps-details';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {retry, timeout} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DetailsService {

  constructor(private http: HttpClient) {
  }

  getLocationDetails(): Observable<GoogleMapsDetails> {
    return this.http.get<GoogleMapsDetails>(environment.host + '/details/get-location-details').pipe(timeout(1000), retry(2));
  }

  getHotelDetails(): Observable<GoogleMapsDetails> {
    return this.http.get<GoogleMapsDetails>(environment.host + '/details/get-hotel-details').pipe(timeout(1000), retry(2));
  }
}
