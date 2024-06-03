import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SightsService } from 'src/app/services/sights.service';

@Component({
  selector: 'app-edit-sight',
  templateUrl: './edit-sight.component.html',
  styleUrls: ['./edit-sight.component.scss']
})
export class EditSightComponent implements OnInit {
  editSightForm: FormGroup = this.formBuilder.group({
    ime: ['', Validators.required],
    opis: ['', Validators.required],
    slika: ['', Validators.required],
    admin: ['', Validators.required]
  });
  submitted = false;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private sightsService: SightsService
  ) { }

  ngOnInit(): void {
    this.editSightForm = this.formBuilder.group({
      ime: ['', Validators.required],
      opis: ['', Validators.required],
      slika: ['', Validators.required],
      admin: ['', Validators.required]
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.sightsService.getZnamenitost(id).subscribe(data => {
        this.editSightForm.patchValue(data);
      });
    }
  }

  get formControls() {
    return this.editSightForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.editSightForm.invalid) {
      return;
    }

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.sightsService.updateZnamenitost(id, this.editSightForm.value).subscribe(
        response => {
          this.successMessage = 'Znamenitost uspješno ažurirana!';
          this.router.navigate(['/znamenitosti']);
        },
        error => {
          this.errorMessage = 'Došlo je do greške prilikom ažuriranja znamenitosti.';
        }
      );
    }
  }
}
