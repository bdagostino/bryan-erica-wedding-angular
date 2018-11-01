import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OurStoryComponent} from './stories/our-story/our-story.component';
import {HomeComponent} from './home/home.component';
import {AccommodationsComponent} from './details/accommodations/accommodations.component';
import {LocationComponent} from './details/location/location.component';
import {RsvpSearchComponent} from './rsvp/rsvp-search/rsvp-search.component';
import {RsvpFormComponent} from './rsvp/rsvp-form/rsvp-form.component';

const routes: Routes = [
  {path: '', component: HomeComponent, pathMatch: 'full'},
  {
    path: 'stories',
    children: [
      {path: 'our-story', component: OurStoryComponent}
    ]
  },
  {
    path: 'details',
    children: [
      {path: 'accommodations', component: AccommodationsComponent},
      {path: 'location', component: LocationComponent}
    ]
  },
  {
    path: 'rsvp',
    children: [
      {path: 'search', component: RsvpSearchComponent},
      {path: 'modify', component: RsvpFormComponent}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {enableTracing: true})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
