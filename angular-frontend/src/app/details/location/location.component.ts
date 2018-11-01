import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  googleMapsUrl = 'https://www.google.com/maps/embed/v1/place?key=AIzaSyCf129o0UMKrMFrIz8hLAtUf-Dl_sCGhik&q=6111 Landerhaven Dr, Mayfield Heights, OH 44124';
}
