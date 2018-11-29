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
import {AgGridModule} from 'ag-grid-angular';
import {FoodComponent} from './admin/food/food.component';
import {GuestComponent} from './admin/guest/guest.component';
import {InvitationComponent} from './admin/invitation/invitation.component';
import {FoodModalComponent} from './admin/food/food-modal/food-modal.component';
import { EditDeleteRendererComponent } from './admin/modal/edit-delete-renderer/edit-delete-renderer.component';

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
    FoodModalComponent,
    EditDeleteRendererComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    AgGridModule.withComponents([EditDeleteRendererComponent])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
