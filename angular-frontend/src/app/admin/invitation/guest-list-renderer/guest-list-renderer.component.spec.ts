import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestListRendererComponent } from './guest-list-renderer.component';

describe('GuestListRendererComponent', () => {
  let component: GuestListRendererComponent;
  let fixture: ComponentFixture<GuestListRendererComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuestListRendererComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuestListRendererComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
