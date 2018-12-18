import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {FoodService} from "../food.service";
import {ActivatedRoute, Router} from "@angular/router";
import {log} from "util";
import {Food} from "../../../common-models/food";

@Component({
  selector: 'app-edit-food',
  templateUrl: './edit-food.component.html',
  styleUrls: ['./edit-food.component.css']
})
export class EditFoodComponent implements OnInit {
  editFoodForm: FormGroup;

  constructor(private foodService: FoodService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.editFoodForm = new FormGroup({
      'foodId': new FormControl(),
      'foodType': new FormControl(),
      'foodDescription': new FormControl()
    });

    this.route.queryParamMap.subscribe(paramMap => {
      this.foodService.getFoodById(Number(paramMap.get('id'))).subscribe(retrievedFood => {
        this.editFoodForm.get('foodId').setValue(retrievedFood.id);
        this.editFoodForm.get('foodType').setValue(retrievedFood.type);
        this.editFoodForm.get('foodDescription').setValue(retrievedFood.description)
      });
    });
  }

  onSubmit() {
    log('Submit Clicked');
    if (this.editFoodForm.valid) {
      log('Form Valid');
      let food = new Food();
      food.id = this.editFoodForm.get('foodId').value;
      food.type = this.editFoodForm.get('foodType').value;
      food.description = this.editFoodForm.get('foodDescription').value;
      this.foodService.updateFood(food).subscribe(success => {
        this.router.navigate(['/admin/food']);
      });

      //this.router.navigate(['/admin/food'], {queryParams: {invitationCode: this.rsvpSearchForm.get('invitationCode').value}});
    } else {
      Object.keys(this.editFoodForm.controls).forEach(field => {
        const control = this.editFoodForm.get(field);
        if (control instanceof FormControl) {
          control.markAsTouched({onlySelf: true});
        }
      });
    }
  }

}
