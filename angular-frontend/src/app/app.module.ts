import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {NavbarComponent} from './navbar/navbar.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HttpClientModule} from '@angular/common/http';
import {OurStoryComponent} from './stories/our-story/our-story.component';
import {HomeComponent} from './home/home.component';
import {AccommodationsComponent} from './details/accommodations/accommodations.component';
import {LocationComponent} from './details/location/location.component';
import {RsvpSearchComponent} from './rsvp/rsvp-search/rsvp-search.component';
import {RsvpFormComponent} from './rsvp/rsvp-form/rsvp-form.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {BridesmaidsComponent} from './stories/bridesmaids/bridesmaids/bridesmaids.component';
import {FoodComponent} from './admin/food/food.component';
import {GuestComponent} from './admin/guest/guest.component';
import {InvitationComponent} from './admin/invitation/invitation.component';
import {GuestListRendererComponent} from './admin/invitation/guest-list-renderer/guest-list-renderer.component';
import {AddFoodComponent} from './admin/food/add-food/add-food.component';
import { EditFoodComponent } from './admin/food/edit-food/edit-food.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    OurStoryComponent,
    HomeComponent,
    AccommodationsComponent,
    LocationComponent,
    RsvpSearchComponent,
    RsvpFormComponent,
    BridesmaidsComponent,
    FoodComponent,
    GuestComponent,
    InvitationComponent,
    GuestListRendererComponent,
    AddFoodComponent,
    EditFoodComponent
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
