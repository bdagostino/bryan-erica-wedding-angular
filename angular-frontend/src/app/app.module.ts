import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {NavbarComponent} from './navbar/navbar.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HttpClientModule} from '@angular/common/http';
import { OurStoryComponent } from './stories/our-story/our-story.component';
import { HomeComponent } from './home/home.component';
import { AccommodationsComponent } from './details/accommodations/accommodations.component';
import { LocationComponent } from './details/location/location.component';
import { RsvpSearchComponent } from './rsvp/rsvp-search/rsvp-search.component';
import { RsvpFormComponent } from './rsvp/rsvp-form/rsvp-form.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    OurStoryComponent,
    HomeComponent,
    AccommodationsComponent,
    LocationComponent,
    RsvpSearchComponent,
    RsvpFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
