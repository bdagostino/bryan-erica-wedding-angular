import {Component, OnInit} from '@angular/core';
import {HomeService} from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private homeService: HomeService) {
  }

  remainingDays = 'Loading';
  image2;
  image1;
  image3;

  ngOnInit() {
    this.homeService.getDaysRemaining().subscribe(data => this.remainingDays = data, error1 => this.remainingDays = 'Error retrieving');
    this.image2 = this.homeService.getImage2();
    this.image1 = this.homeService.getImage1();
    this.image3 = this.homeService.getImage3();
  }
}
