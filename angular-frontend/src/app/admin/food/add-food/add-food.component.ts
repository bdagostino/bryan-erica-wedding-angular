import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {log} from 'util';
import {Food} from "../../../common-models/food";
import {FoodService} from "../../../services/food.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-food',
  templateUrl: './add-food.component.html',
  styleUrls: ['./add-food.component.css']
})
export class AddFoodComponent implements OnInit {

  addFoodForm: FormGroup;

  constructor(private foodService: FoodService, private router: Router) { }

  ngOnInit() {
    this.addFoodForm = new FormGroup({
      'foodType': new FormControl(),
      'foodDescription': new FormControl()
    });
  }

  onSubmit(){
    log('Submit Clicked');
    if(this.addFoodForm.valid){
      log('Form Valid');
      let food = new Food();
      food.type = this.addFoodForm.get('foodType').value;
      food.description = this.addFoodForm.get('foodDescription').value;
      this.foodService.createFood(food).subscribe(success => {
        this.router.navigate(['/admin/food']);
      });

      //this.router.navigate(['/admin/food'], {queryParams: {invitationCode: this.rsvpSearchForm.get('invitationCode').value}});
    }else{
      Object.keys(this.addFoodForm.controls).forEach(field => {
        const control = this.addFoodForm.get(field);
        if (control instanceof FormControl) {
          control.markAsTouched({onlySelf: true});
        }
      });
    }
  }
}
