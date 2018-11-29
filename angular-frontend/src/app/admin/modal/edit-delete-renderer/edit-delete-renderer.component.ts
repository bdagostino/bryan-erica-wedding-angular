import {Component} from '@angular/core';
import {AgRendererComponent} from 'ag-grid-angular';
import {IAfterGuiAttachedParams, ICellRendererParams} from 'ag-grid-community';

@Component({
  selector: 'app-edit-delete-renderer',
  templateUrl: './edit-delete-renderer.component.html',
  styleUrls: ['./edit-delete-renderer.component.css']
})
export class EditDeleteRendererComponent implements AgRendererComponent {
  public params: any;

  constructor() {
  }

  afterGuiAttached(params?: IAfterGuiAttachedParams): void {
  }

  agInit(params: ICellRendererParams): void {
    this.params = params;
  }

  refresh(params: any): boolean {
    return false;
  }

  editRow() {
    this.params.context.componentParent.editRow(this.params.node.data);
  }

  deleteRow() {
    this.params.context.componentParent.deleteRow(this.params.node.data);
  }

}
