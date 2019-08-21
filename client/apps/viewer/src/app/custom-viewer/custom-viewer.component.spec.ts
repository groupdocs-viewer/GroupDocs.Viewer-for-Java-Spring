import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomViewerComponent } from './custom-viewer.component';

describe('CustomViewerComponent', () => {
  let component: CustomViewerComponent;
  let fixture: ComponentFixture<CustomViewerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomViewerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
