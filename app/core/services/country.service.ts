// src/app/core/services/country.service.ts
import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Country } from '../models/country.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CountryService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/countries';

  getAll(): Observable<Country[]> {
    return this.http.get<Country[]>(this.baseUrl);
  }

  getByName(name: string): Observable<Country> {
    return this.http.get<Country>(`${this.baseUrl}/${name}`);
  }

  searchByCapital(capital: string): Observable<Country[]> {
    return this.http.get<Country[]>(`${this.baseUrl}/capital/${capital}`);
  }

  searchByPopulation(pop: number): Observable<Country[]> {
    return this.http.get<Country[]>(`${this.baseUrl}/population/${pop}`);
  }
}
