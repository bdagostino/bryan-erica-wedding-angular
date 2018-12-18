import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {EditDeleteRendererComponent} from "../modal/edit-delete-renderer/edit-delete-renderer.component";
import {GuestListRendererComponent} from "./guest-list-renderer/guest-list-renderer.component";

@Component({
  selector: 'app-invitation',
  templateUrl: './invitation.component.html',
  styleUrls: ['./invitation.component.css']
})
export class InvitationComponent implements OnInit {

  private gridApi;
  private gridColumnApi;

  constructor(private http: HttpClient) {
  }

  rowData;

  context = {componentParent: this};
  columnDefs = [
    {headerName: 'Id', field: 'id', hide: true, lockVisible: true},
    {headerName: 'Guest List', cellRendererFramework: GuestListRendererComponent, autoHeight: true},
    {headerName: 'Max Guests', field: 'maxGuests'},
    {headerName: 'Invitation Code', field: 'invitationCode'},
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

    this.rowData = null;
  }

}
