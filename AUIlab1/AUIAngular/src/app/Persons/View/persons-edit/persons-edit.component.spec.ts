import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonsEditComponent } from './persons-edit.component';

describe('PersonsEditComponent', () => {
  let component: PersonsEditComponent;
  let fixture: ComponentFixture<PersonsEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PersonsEditComponent]
    });
    fixture = TestBed.createComponent(PersonsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
