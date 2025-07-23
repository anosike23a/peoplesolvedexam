import { Component, inject } from '@angular/core';
import { CountryService } from '../../services/country.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Country } from '../../models/country.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-detail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.scss'
})
export class DetailComponent {
 
  country: Country | null = null;
  isLoading = true;

  constructor(
    private countryService: CountryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id = this.route.snapshot.params['id']
      if (id) {
        this.loadCountryDetails(id);
      } else {
        this.router.navigate(['/detail']);
      }
    });
  }

  loadCountryDetails(id: number) {
    this.isLoading = true;
    this.countryService.getCountryById(id).subscribe({
      next: (country) => {
        this.country = country;
        this.isLoading = false;
      },
      error: () => {
        this.router.navigate(['/']);
      }
    });
  }

  editCountry() {
    if (this.country) {
      this.router.navigate(['/edit', this.country.id]);
    }
  }

  deleteCountry() {
    if (this.country && confirm('Are you sure you want to delete this country?')) {
      this.countryService.deleteCountry(this.country.id).subscribe({
        next: () => {
          this.router.navigate(['/']);
        },
        error: (err) => {
          console.error('Error deleting country', err);
        }
      });
    }
  }
}

