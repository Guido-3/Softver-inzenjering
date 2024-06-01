import { Component, OnInit } from '@angular/core';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.scss']
})

export class ReservationsComponent implements OnInit{
  reservations: any = []

  constructor(private sightsService: ReservationService) { }


  ngOnInit(): void {
    console.log('ngOnInit called');
    this.sightsService.getSights().subscribe(
      data => {
        console.log('Data received:', data);
        this.reservations = data;
      },
      error => {
        console.error('Error fetching sights data', error);
      }
    );
  }
}