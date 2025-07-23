import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable } from 'rxjs';
import { Country } from '../models/country.model';

@Injectable({ providedIn: 'root' })
export class CountryService {
  private apiUrl = 'http://localhost:8080/api/countries';

  constructor(private http: HttpClient) {}

  getCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(this.apiUrl);
  }

  getCountryById(id: number): Observable<Country> {
    return this.http.get<Country>(`${this.apiUrl}/${id}`);
  }

  getCountriesByRegion(region: string): Observable<Country[]> {
    return this.http.get<Country[]>(`${this.apiUrl}/region/${region}`);
  }

  //createCountry(countryData: FormData): Observable<Country> {
    //return this.http.post<Country>(this.apiUrl, countryData);
  //}
  createCountry(countryData: FormData): Observable<Country> {
  return this.http.post<Country>(this.apiUrl, countryData).pipe(
    catchError(error => {
      if (error.status === 409) {
        throw new Error('A country with this name already exists');
      }
      throw error;
    })
  );
}

  updateCountry(id: number, countryData: FormData): Observable<Country> {
    return this.http.put<Country>(`${this.apiUrl}/${id}`, countryData);
  }

  deleteCountry(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}