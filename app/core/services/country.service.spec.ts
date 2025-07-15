import { HttpClient } from "@angular/common/http";
import { CountryService } from "./country.service";
import { TestBed } from "@angular/core/testing";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { Country } from "../models/country.model";

describe('CountryService', () => {
  let service: CountryService;
  let http: HttpClient;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CountryService]
    });
    service = TestBed.inject(CountryService);
    http = TestBed.inject(HttpClient);
  });

  it('should fetch all countries', () => {
    const mockData: Country[] = [{ name: 'Kenya', capital: 'Nairobi', population: 50000000, flagUrl: '/flags/kenya.png' }];
    //const req = httpMock.expectOne('http://localhost:8080/countries');
    //expect(req.request.method).toBe('GET');
    //req.flush(mockData);
  });
});
