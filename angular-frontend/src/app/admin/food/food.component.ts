import {Component, OnInit} from '@angular/core';
import {log} from 'util';
import {FoodService} from "../../services/food.service";
import {Food} from "../../common-models/food";
import {Router} from "@angular/router";

@Component({
  selector: 'app-food',
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.css']
})
export class FoodComponent implements OnInit {


  constructor(private foodService: FoodService, private router: Router) {
  }

  foodSavedStatus: number;
  staticAlertClosed = false;
  foods;


  ngOnInit() {
    this.foodService.getAllFoodAdmin().subscribe(data => {
      this.foods = data;
    });
  }

  deleteRow(food: Food) {
    log('Delete Row is called for: ' + food.id);
    this.foodService.deleteFoodById(food.id).subscribe(success => {
      this.foodService.getAllFoodAdmin().subscribe(data => {
        this.foods = data;
      });
    });
  }

  editRow(food: Food) {
    log('Edit Row is called for: ' + food.id);
    this.router.navigate(['/admin/food/edit-food'], {queryParams: {id: food.id}});
  }
}
