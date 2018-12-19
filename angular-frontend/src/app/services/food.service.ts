import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Food} from '../common-models/food';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  constructor(private http: HttpClient) {
  }

  getAllFoodAdmin(): Observable<Food[]> {
    return this.http.get<Food[]>(environment.host + '/admin/foods');
  }

  deleteFoodById(id: number): Observable<void> {
    return this.http.delete<void>(environment.host + '/admin/foods/' + id);
  }

  createFood(request: Food): Observable<void> {
    return this.http.post<void>(environment.host + '/admin/foods', request);
  }

  updateFood(request: Food): Observable<void> {
    return this.http.put<void>(environment.host + '/admin/foods/' + request.id, request);
  }

  getFoodById(id: number): Observable<Food> {
    return this.http.get<Food>(environment.host + '/admin/foods/' + id);
  }

  getAllFood(): Observable<Food[]> {
    return this.http.get<Food[]>(environment.host + '/foods');
  }
}
