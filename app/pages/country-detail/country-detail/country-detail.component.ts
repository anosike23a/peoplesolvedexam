// country-detail.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CountryService } from '../../../core/services/country.service';
import { Country } from '../../../core/models/country.model';

@Component({
  standalone: true,
  selector: 'app-country-detail',
  templateUrl: './country-detail.component.html',
  styleUrls: ['./country-detail.component.scss'],
})
export class CountryDetailComponent implements OnInit {
  
  country?: Country;

  constructor(private route: ActivatedRoute, private service: CountryService) {}

  ngOnInit() {
    const name = this.route.snapshot.paramMap.get('name')!;
    this.service.getByName(name).subscribe(data => this.country = data);
  }
}
