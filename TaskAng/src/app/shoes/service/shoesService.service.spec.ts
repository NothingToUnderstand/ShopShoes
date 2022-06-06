/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ShoesServiceService } from './shoesService.service';

describe('Service: ShoesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ShoesServiceService]
    });
  });

  it('should ...', inject([ShoesServiceService], (service: ShoesServiceService) => {
    expect(service).toBeTruthy();
  }));
});
