import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home/home.component';
import { CountryDetailComponent } from './pages/country-detail/country-detail/country-detail.component';
import { SearchComponent } from './pages/search/search/search.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'country/:name', component: CountryDetailComponent },
  { path: 'search', component: SearchComponent },
];
