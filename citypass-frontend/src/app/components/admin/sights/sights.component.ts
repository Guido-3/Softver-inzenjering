import { Component, OnInit } from '@angular/core';
import { SightsService } from 'src/app/services/sights.service';
import { HttpClient } from '@angular/common/http';
import { Znamenitost } from 'src/app/components/app/models/znamenitost.model';  // Adjust path if necessary


@Component({
  selector: 'app-sights',
  templateUrl: './sights.component.html',
  styleUrls: ['./sights.component.scss']
})
export class SightsComponent implements OnInit{
  znamenitosti: Znamenitost[] = []

  constructor(private sightsService: SightsService, private http: HttpClient) { }


  ngOnInit(): void {
    console.log('ngOnInit called');
    this.sightsService.getSights().subscribe(
      (data : Znamenitost[]) => {
        console.log('Data received:', data);
        this.znamenitosti = data;
      },
      error => {
        console.error('Error fetching sights data', error);
      }
    );
  }

  deleteZnamenitost(id: number): void {
    if (confirm('Da li ste sigurni da želite obrisati znamenitost?')) {
      this.http.delete(`http://localhost:8080/citypass-api/znamenitost/${id}`)
        .subscribe(response => {
          //console.log("Uzeta znamenitost sa idijem" + id)
          // Update the list to remove the deleted item
          this.znamenitosti = this.znamenitosti.filter((znamenitost:Znamenitost) => znamenitost.id !== id);
        });
    }
  }
}
