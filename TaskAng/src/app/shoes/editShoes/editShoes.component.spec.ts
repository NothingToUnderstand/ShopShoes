/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { EditShoesComponent } from './editShoes.component';

describe('EditShoesComponent', () => {
  let component: EditShoesComponent;
  let fixture: ComponentFixture<EditShoesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditShoesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditShoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
