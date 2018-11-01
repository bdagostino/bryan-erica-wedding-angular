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
    return this.http.get<string>('http://' + environment.host + ':8080/getRemainingDays').pipe(timeout(1000), retry(2));
  }

  getImage1() {
    return 'http://' + environment.host + ':8080/getImage1';
    // return 'assets/img/home/P1010144.JPG';
  }

  getImage2() {
    return 'http://' + environment.host + ':8080/getImage2';
  }

  getImage3() {
    return 'http://' + environment.host + ':8080/getImage3';
  }

  loadCarouselImages() {

  }
}
