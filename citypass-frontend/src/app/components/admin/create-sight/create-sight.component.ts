import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SightsService } from 'src/app/services/sights.service';
 // Pretpostavimo da postoji SightService za komunikaciju sa backendom

@Component({
  selector: 'app-create-sight',
  templateUrl: './create-sight.component.html',
  // styleUrls: ['./create-sight.component.css']
})
export class CreateSightComponent implements OnInit {
  sightForm: FormGroup;
  submitted = false;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private sightService: SightsService
  ) {
    this.sightForm = this.formBuilder.group({
      ime: ['', Validators.required],
      opis: ['', Validators.required],
      slika: ['', Validators.required],
      admin: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  get formControls() { return this.sightForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    if (this.sightForm.invalid) {
      return;
    }

    this.sightService.createZnamenitost(this.sightForm.value).subscribe({
      next: (response) => {
        this.successMessage = 'Znamenitost je uspješno kreirana!';
        this.errorMessage = '';
        this.sightForm.reset();
        this.submitted = false;
      },
      error: (error) => {
        this.errorMessage = 'Došlo je do greške prilikom kreiranja znamenitosti.';
        this.successMessage = '';
      }
    });
  }
}
