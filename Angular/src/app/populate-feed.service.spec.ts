import { TestBed } from '@angular/core/testing';

import { PopulateFeedService } from './populate-feed.service';

describe('PopulateFeedService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PopulateFeedService = TestBed.get(PopulateFeedService);
    expect(service).toBeTruthy();
  });
});
