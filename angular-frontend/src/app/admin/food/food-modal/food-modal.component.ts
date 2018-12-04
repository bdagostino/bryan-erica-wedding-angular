import {Component, EventEmitter, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {log} from 'util';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {BaseModal} from '../../modal/base-modal.abstract';
import {FormControl, FormGroup} from '@angular/forms';
import {FoodService} from '../food.service';
import {Food} from '../../../common-models/food';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-food-modal',
  templateUrl: './food-modal.component.html',
  styleUrls: ['./food-modal.component.css']
})
export class FoodModalComponent extends BaseModal implements OnInit {
  @ViewChild('foodModalTemplate')
  modalTemplate: TemplateRef<NgbModal>;
  _modalTitle: string;
  _foodModifyForm: FormGroup;
  _foodId: number;
  ngModalRef: NgbModalRef;

  @Output() saveEvent = new EventEmitter<number>();

  public constructor(private modalService: NgbModal, private foodService: FoodService, private route: ActivatedRoute, private router: Router) {
    super(modalService);
    log('Food Modal Component Constructor');
  }

  ngOnInit() {
    this._foodModifyForm = new FormGroup({
      'foodType': new FormControl(),
      'foodDescription': new FormControl()
    });
  }

  set modalTitle(title: string) {
    this._modalTitle = title;
  }

  set foodType(foodType: string) {
    this._foodModifyForm.get('foodType').setValue(foodType);
  }

  set foodDescription(foodDescription: string) {
    this._foodModifyForm.get('foodDescription').setValue(foodDescription);
  }

  set foodId(id: number) {
    this._foodId = id;
  }

  get foodType() {
    return this._foodModifyForm.get('foodType').value;
  }

  get foodDescription() {
    return this._foodModifyForm.get('foodDescription').value;
  }

  openModal() {
    this.ngModalRef = super.openModalBase(this.modalTemplate);
  }

  saveModal() {
    if (this._foodModifyForm.valid) {
      const foodModifyRequest = new Food();
      foodModifyRequest.id = this._foodId;
      foodModifyRequest.type = this._foodModifyForm.get('foodType').value;
      foodModifyRequest.description = this._foodModifyForm.get('foodDescription').value;
      const response = this.foodService.saveFood(foodModifyRequest);
      response.subscribe(value => {
        this.saveEvent.emit(0);
        this.closeModal();
        this.clearFormData();
      }, error => {
        log(error);
      });
    } else {
      this._foodModifyForm.get('foodType').markAsTouched();
      this._foodModifyForm.get('foodDescription').markAsTouched();
    }
  }

  closeModal() {
    this.clearFormData();
    super.closeModalBase(this.ngModalRef);
  }

  clearFormData() {
    this._foodId = null;
    this._foodModifyForm = new FormGroup({
      'foodType': new FormControl(),
      'foodDescription': new FormControl()
    });
  }


}
