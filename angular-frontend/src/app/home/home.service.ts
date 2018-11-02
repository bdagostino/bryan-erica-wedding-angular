import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {retry, timeout} from 'rxjs/operators';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) {
  }

  getDaysRemaining(): Observable<string> {
    return this.http.get<string>(environment.host + '/getRemainingDays').pipe(timeout(1000), retry(2));
  }

  getImage1() {
    return 'https://passcdn-cf1.pass.us/07s5h3417260/m4Wq5540940376l.jpg';
    // return 'http://' + environment.host + ':8080/getImage1';
  }

  getImage2() {
    return 'https://passcdn-cf1.pass.us/07s5h3417260/8Stlq540940628l.jpg';
    // return 'http://' + environment.host + ':8080/getImage2';
  }

  getImage3() {
    return 'https://passcdn-cf1.pass.us/07s5h3417260/kc3qh540940660l.jpg';
    // return 'http://' + environment.host + ':8080/getImage3';
  }

  loadCarouselImages() {

  }
}
