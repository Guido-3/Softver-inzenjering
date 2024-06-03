import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CreateSightService } from 'src/app/services/create-sight.service';

@Component({
  selector: 'app-create-sight',
  templateUrl: './create-sight.component.html',
  styleUrls: ['./create-sight.component.scss']
})
export class CreateSightComponent implements OnInit{
  sightForm: FormGroup;
  submitted = false;
  successMessage : string = '';
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private createSightService: CreateSightService) {
    this.sightForm = this.fb.group({
      ime: ['', Validators.required],
      opis: ['', Validators.required],
      slika: ['', Validators.required],
      admin: ['', Validators.required],
    });
  }

  ngOnInit(): void {
      
  }
  
  get formControls() {
    return this.sightForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    this.successMessage = '';
    this.errorMessage = '';

    if ( this.sightForm.valid) {
      this.createSightService.createSight(this.sightForm.value).subscribe(
        response => {
          this.successMessage = 'Sight created successfully';
        },
        error => {
          this.errorMessage = 'Error'
        }
      );
    }
  }

}
