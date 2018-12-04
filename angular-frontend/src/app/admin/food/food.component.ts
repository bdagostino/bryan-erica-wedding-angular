import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FoodModalComponent} from './food-modal/food-modal.component';
import {environment} from '../../../environments/environment';
import {log} from 'util';
import {EditDeleteRendererComponent} from '../modal/edit-delete-renderer/edit-delete-renderer.component';

@Component({
  selector: 'app-food',
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.css']
})
export class FoodComponent implements OnInit {
  private gridApi;
  private gridColumnApi;

  constructor(private http: HttpClient) {
  }

  @ViewChild('foodModal')
  modalEdit: FoodModalComponent;

  rowData;
  rowSelection = 'single';


  context = {componentParent: this};
  columnDefs = [
    {headerName: 'Id', field: 'id', hide: true, lockVisible: true},
    // {headerName: 'Id', field: 'id'},
    {headerName: 'Type', field: 'type'},
    {headerName: 'Description', field: 'description'},
    {headerName: 'Action', cellRendererFramework: EditDeleteRendererComponent, autoHeight: true}
  ];

  foodSavedStatus: number;
  staticAlertClosed = false;


  ngOnInit() {

  }

  onGridReady(params) {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.gridApi.sizeColumnsToFit();

    this.http.get(environment.host + '/admin/foods')
      .subscribe(data => {
        this.rowData = data;
      });
  }

  editRow(nodeData: any) {
    this.modalEdit.modalTitle = 'Edit Food';
    this.modalEdit.foodId = nodeData.id;
    this.modalEdit.foodType = nodeData.type;
    this.modalEdit.foodDescription = nodeData.description;
    this.modalEdit.openModal();
  }

  deleteRow(nodeData: any) {
    log('Delete Row is called for: ' + nodeData.id);
    this.http.delete(environment.host + '/admin/foods/' +nodeData.id).subscribe();

  }

  addFood() {
    this.modalEdit.modalTitle = 'Add Food';
    this.modalEdit.openModal();
  }

  saveFood(status: number) {
    this.foodSavedStatus = status;
    if(status === 0){
      setTimeout(() => this.staticAlertClosed = true, 2000);
      this.http.get(environment.host + '/admin/foods')
        .subscribe(data => {
          this.rowData = data;
        });
    }
  }
}
