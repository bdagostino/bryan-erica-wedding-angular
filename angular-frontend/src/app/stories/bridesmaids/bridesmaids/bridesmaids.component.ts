import {Component, OnInit} from '@angular/core';
import {StoriesService} from '../../stories.service';

@Component({
  selector: 'app-bridesmaids',
  templateUrl: './bridesmaids.component.html',
  styleUrls: ['./bridesmaids.component.css']
})
export class BridesmaidsComponent implements OnInit {

  constructor(private storiesService: StoriesService) {
  }

  maidOfHonorImage = null;
  bridesmaid1;
  bridesmaid2;
  bridesmaid3;

  ngOnInit() {
    this.maidOfHonorImage = this.storiesService.getMaidOfHonor();
    this.bridesmaid1 = this.storiesService.getBridesmaid1();
    this.bridesmaid2 = this.storiesService.getBridesmaid2();
    this.bridesmaid3 = this.storiesService.getBridesmaid3();
  }

}
