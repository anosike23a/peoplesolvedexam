import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CountryService } from '../../services/country.service';
import { Country } from '../../models/country.model';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-detail',
  standalone: true,
  imports: [CommonModule,RouterLink],
  templateUrl: './country-detail.component.html',
  styleUrls: ['./country-detail.component.scss']
})
export class DetailComponent {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private countryService = inject(CountryService);
  
  country: Country | null = null;
  
  ngOnInit() {
    this.route.params.pipe(
       //this.id = this.route.snapshot.paramMap.get('id'); //get id parameter this.route.snapshot.params['id']
      switchMap(params => this.countryService.getCountryById(this.route.snapshot.params['id']))
    ).subscribe({
      next: country => this.country = country,
      error: () => this.router.navigate(['/'])
    });
  }
}