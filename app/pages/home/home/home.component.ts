// home.component.ts
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Country } from '../../../core/models/country.model';
import { CountryService } from '../../../core/services/country.service';

@Component({
  standalone: true,
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  countries: Country[] = [];

  constructor(private service: CountryService, private router: Router) {}

  ngOnInit() {
    this.service.getAll().subscribe(data => this.countries = data);
  }

  openCountry(name: string) {
    this.router.navigate(['/country', name]);
  }
}

