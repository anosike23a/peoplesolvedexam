import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Country } from '../../models/country.model';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-country-card',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './country-card.component.html',
  styleUrl: './country-card.component.scss'
})
export class CountryCardComponent {


  @Input() country!: Country;
}
