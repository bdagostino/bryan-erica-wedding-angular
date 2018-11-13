import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StoriesService {

  constructor(private httpClient: HttpClient) {
  }

  getMaidOfHonor() {
    return environment.host + '/stories/load-maid-honor-image';
  }

  getBridesmaid1() {
    return environment.host + '/stories/load-bridesmaid-1-image';
  }

  getBridesmaid2() {
    return environment.host + '/stories/load-bridesmaid-2-image';
  }

  getBridesmaid3() {
    return environment.host + '/stories/load-bridesmaid-3-image';
  }

}
