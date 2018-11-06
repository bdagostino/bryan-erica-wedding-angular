import {Component, OnInit} from '@angular/core';
import {DetailsService} from '../details.service';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';

@Component({
  selector: 'app-accommodations',
  templateUrl: './accommodations.component.html',
  styleUrls: ['./accommodations.component.css']
})
export class AccommodationsComponent implements OnInit {

  constructor(private detailsService: DetailsService, private domSanitizer: DomSanitizer) {
  }

  googleMapsUrl: SafeResourceUrl;
  loadingMessage = 'Loading Google Maps...';

  ngOnInit() {
    this.detailsService.getHotelDetails().subscribe(googleMapDetails => {
      this.googleMapsUrl = this.domSanitizer.bypassSecurityTrustResourceUrl('https://www.google.com/maps/embed/v1/place?key='
        + googleMapDetails.googleApiKey + '&q=' + googleMapDetails.searchAddress);
    }, error => {
      this.loadingMessage = 'Error Loading Google Maps';
      console.log(error);
    });
  }
}
