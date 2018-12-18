import { Component, OnInit } from '@angular/core';
import {AgRendererComponent} from 'ag-grid-angular';
import {IAfterGuiAttachedParams, ICellRendererParams} from 'ag-grid-community';

@Component({
  selector: 'app-guest-list-renderer',
  templateUrl: './guest-list-renderer.component.html',
  styleUrls: ['./guest-list-renderer.component.css']
})
export class GuestListRendererComponent implements AgRendererComponent {

  constructor() { }

  afterGuiAttached(params?: IAfterGuiAttachedParams): void {
  }

  agInit(params: ICellRendererParams): void {
  }

  refresh(params: any): boolean {
    return false;
  }


}
