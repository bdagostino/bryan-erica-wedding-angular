import {Component, OnInit} from '@angular/core';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {DetailsService} from '../details.service';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {

  constructor(private detailsService: DetailsService, private domSanitizer: DomSanitizer) {
  }

  googleMapsUrl: SafeResourceUrl;
  loadingMessage = 'Loading Google Maps...';

  ngOnInit() {
    this.detailsService.getLocationDetails().subscribe(googleMapDetails => {
      this.googleMapsUrl = this.domSanitizer.bypassSecurityTrustResourceUrl('https://www.google.com/maps/embed/v1/place?key='
        + googleMapDetails.googleApiKey + '&q=' + googleMapDetails.searchAddress);
    }, error => {
      this.loadingMessage = 'Error Loading Google Maps';
      console.log(error);
    });
  }
}
