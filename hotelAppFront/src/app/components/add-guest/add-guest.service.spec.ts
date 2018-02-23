import { TestBed, inject } from '@angular/core/testing';

import { AddGuestService } from './add-guest.service';

describe('AddGuestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddGuestService]
    });
  });

  it('should be created', inject([AddGuestService], (service: AddGuestService) => {
    expect(service).toBeTruthy();
  }));
});
