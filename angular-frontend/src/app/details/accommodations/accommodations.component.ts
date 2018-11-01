import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-accommodations',
  templateUrl: './accommodations.component.html',
  styleUrls: ['./accommodations.component.css']
})
export class AccommodationsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  googleMapsUrl = 'https://www.google.com/maps/embed/v1/place?key=AIzaSyCf129o0UMKrMFrIz8hLAtUf-Dl_sCGhik&q=6103 Landerhaven Dr, Mayfield Heights, OH 44124';
  key = 'AIzaSyCf129o0UMKrMFrIz8hLAtUf-Dl_sCGhik';
  address = '6103 Landerhaven Dr, Mayfield Heights, OH 44124';




}
