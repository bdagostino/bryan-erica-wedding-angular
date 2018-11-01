import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RsvpSearchComponent } from './rsvp-search.component';

describe('RsvpSearchComponent', () => {
  let component: RsvpSearchComponent;
  let fixture: ComponentFixture<RsvpSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RsvpSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RsvpSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
