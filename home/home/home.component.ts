import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CountryService } from '../../services/country.service';
import { Country } from '../../models/country.model';
import { FormsModule } from '@angular/forms';
import { CountryCardComponent } from '../../components/country-card/country-card.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, CountryCardComponent, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  private countryService = inject(CountryService);
  
  countries: Country[] = [];
  filteredCountries: Country[] = [];
  regions: string[] = ['All', 'Africa', 'Americas', 'Asia', 'Europe', 'Oceania'];
  selectedRegion = 'All';
  searchTerm = '';

  ngOnInit() {
    this.countryService.getCountries().subscribe({
      next: (response: Country[]) => {
        this.countries = response;
        this.filteredCountries = [...response];
      },
      error: (error: any) => console.error('Error loading countries', error)
    });
  }

  filterCountries() {
    this.filteredCountries = this.countries.filter(country => {
      const matchesRegion = this.selectedRegion === 'All' || country.region === this.selectedRegion;
      const matchesSearch = country.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
                           country.capital.toLowerCase().includes(this.searchTerm.toLowerCase());
      return matchesRegion && matchesSearch;
    });
  }

  trackByCountryId(index: number, country: Country): number {
    return country.id;
  }
}