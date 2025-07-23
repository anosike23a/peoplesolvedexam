import { Routes } from '@angular/router';
import { CountryFormComponent } from './components/country-form/country-form.component';
import { DetailComponent } from './components/country-detail/country-detail.component';
import { HomeComponent } from './home/home/home.component';

export const routes: Routes = [
  { path: '', component: HomeComponent, title: 'Countries App' },
  { path: 'detail/:id', component: DetailComponent, title: 'Country Details' },
  { path: 'details/:id', component: DetailComponent, title: 'Country Details' },
  { path: 'create', component: CountryFormComponent, title: 'Add Country' },
  { path: 'edit/:id', component: CountryFormComponent, title: 'Edit Country' },
  { path: '**', redirectTo: '' }
];