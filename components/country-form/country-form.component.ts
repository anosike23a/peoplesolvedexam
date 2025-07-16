import { Component, OnInit, Input, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CountryService } from '../../services/country.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Country } from '../../models/country.model';

@Component({
  selector: 'app-country-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './country-form.component.html',
  styleUrls: ['./country-form.component.scss']
})
export class CountryFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private countryService = inject(CountryService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  
  countryForm!: FormGroup;
  isEditMode = false;
  countryId: number | null = null;
  previewImage: string | null = null;

  ngOnInit() {
    this.countryForm = this.fb.group({
      name: ['', Validators.required],
      population: ['', [Validators.required, Validators.min(0)]],
      region: ['', Validators.required],
      capital: ['', Validators.required],
      image: [null]
    });

    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.countryId = +params['id'];
        this.loadCountryData(this.countryId);
      }
    });
  }

  loadCountryData(id: number) {
    this.countryService.getCountryById(id).subscribe({
      next: country => {
        this.countryForm.patchValue({
          name: country.name,
          population: country.population,
          region: country.region,
          capital: country.capital
        });
        this.previewImage = country.imageUrl;
      },
      error: () => this.router.navigate(['/'])
    });
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length) {
      const file = input.files[0];
      this.countryForm.patchValue({ image: file });
      this.countryForm.get('image')?.updateValueAndValidity();
      
      // Create preview
      const reader = new FileReader();
      reader.onload = () => {
        this.previewImage = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

 onSubmit() {
  if (this.countryForm.invalid) return;

  const formData = new FormData();
  // ... form data setup ...

  if (this.isEditMode && this.countryId) {
    // ... existing edit code ...
  } else {
    this.countryService.createCountry(formData).subscribe({
      next: (country) => this.router.navigate(['/detail', country.id]),
      error: (err) => {
        if (err.message === 'A country with this name already exists') {
          this.countryForm.get('name')?.setErrors({ duplicate: true });
        } else {
          console.error('Error creating country', err);
        }
      }
    });
  }
}
}