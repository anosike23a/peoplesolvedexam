// search.component.ts
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Country } from '../../../core/models/country.model';
import { CountryService } from '../../../core/services/country.service';

@Component({
  standalone: true,
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
  imports: [ReactiveFormsModule, CommonModule],
})
export class SearchComponent {
  searchForm = new FormGroup({
    name: new FormControl(''),
    capital: new FormControl(''),
    population: new FormControl(''),
  });

  countries: Country[] = [];

  constructor(private service: CountryService) {}

  search() {
    const { name, capital, population } = this.searchForm.value!;
    if (name) {
      this.service.getByName(name).subscribe(res => this.countries = [res]);
    } else if (capital) {
      this.service.searchByCapital(capital).subscribe(res => this.countries = res);
    } else if (population) {
      this.service.searchByPopulation(+population).subscribe(res => this.countries = res);
    }
  }
}
